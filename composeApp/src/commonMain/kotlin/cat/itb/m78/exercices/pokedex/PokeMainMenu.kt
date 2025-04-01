package cat.itb.m78.exercices.pokedex

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.Rgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun PokedexMainMenu(ToPokedex : () -> Unit){
    Column(Modifier.fillMaxSize().background(color = Color.hsv(349.95f, 0.9f, 0.8f)), Arrangement.Center, Alignment.CenterHorizontally, ) {
        Card(colors = CardDefaults.cardColors(
            containerColor = Color.hsv(193.92f, 0.5f, 0.8f)),
            border = BorderStroke(10.dp, Color.Black),
            onClick = { ToPokedex() },
            modifier = Modifier.size(width = 300.dp, height = 400.dp)
        ){
            Column(Modifier.fillMaxSize().padding(50.dp), Arrangement.Center, Alignment.CenterHorizontally, ) {
                Text("International Pokedex",textAlign = TextAlign.Center, fontSize = 1.8.em, color = Color.White)
                Spacer(Modifier.height(50.dp))
                Text("Click the screen to open the pokedex", textAlign = TextAlign.Center, fontSize = 0.9.em, color = Color.White)
            }
        }
    }
}