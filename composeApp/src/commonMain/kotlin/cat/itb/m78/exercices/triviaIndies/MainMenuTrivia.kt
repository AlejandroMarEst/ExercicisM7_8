package cat.itb.m78.exercices.triviaIndies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun MainMenuTriviaView(ToGame: ()->Unit, ToSettings: ()->Unit){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally){
        Text("TRIVIA", fontSize = 10.em)
        Spacer(Modifier.height(60.dp))
        Button(onClick = {ToGame()}){
            Text("Start Quiz")
        }
        Button(onClick = {ToSettings()}){
            Text("Settings")
        }
    }
}