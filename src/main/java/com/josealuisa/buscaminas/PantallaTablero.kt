package com.josealuisa.buscaminas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josealuisa.buscaminas.ui.theme.BuscaminasTheme

// # oculta · F bandera · * mina · . vacía · 1-8 número
private val disenoTablero = listOf(
    "..11####",
    "..1F####",
    "..122###",
    "...1F###",
    "..1*####",
    ".12#####",
    ".1F#####",
    ".1######"
)

@Composable
fun PantallaTablero() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E2430))
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Buscaminas",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(20.dp))
        BarraSuperior()
        Spacer(Modifier.height(20.dp))
        Tablero()
    }
}

@Composable
fun BarraSuperior() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Indicador("010")
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFC107)),
            contentAlignment = Alignment.Center
        ) {
            Text("🙂", fontSize = 28.sp)
        }
        Indicador("000")
    }
}

@Composable
fun Indicador(texto: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF111111))
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = texto,
            color = Color(0xFFFF5252),
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Tablero() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF2C3442))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        disenoTablero.forEach { fila ->
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                fila.forEach { c ->
                    CasillaView(c, Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun CasillaView(c: Char, modifier: Modifier = Modifier) {
    val fondo = when (c) {
        '#', 'F' -> Color(0xFF9AA5B1)
        '*' -> Color(0xFFE57373)
        else -> Color(0xFFE8EAED)
    }
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(6.dp))
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {
        when {
            c == 'F' -> Text("🚩", fontSize = 18.sp)
            c == '*' -> Text("💣", fontSize = 18.sp)
            c.isDigit() -> Text(
                text = c.toString(),
                color = colorNumero(c),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private fun colorNumero(c: Char): Color = when (c) {
    '1' -> Color(0xFF1E88E5)
    '2' -> Color(0xFF43A047)
    '3' -> Color(0xFFE53935)
    else -> Color(0xFF8E24AA)
}

@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
    BuscaminasTheme {
        PantallaTablero()
    }
}