package cat.itb.m78.exercices.triviaIndies

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.images
import m78exercices.composeapp.generated.resources.tapestry
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainMenuTriviaView(ToGame: ()->Unit, ToSettings: ()->Unit){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally){
        Text("Trivia Indie Games", fontSize = 5.em, color = Color.Cyan)
        Image(
            painter = painterResource(Res.drawable.images),
            contentDescription = null, modifier = Modifier.size(600.dp, 200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(30.dp))
        Button(onClick = {ToGame()}){
            Text("Start Quiz",  color = Color.Blue)
        }
        Button(onClick = {ToSettings()}){
            Text("Settings",  color = Color.Blue)
        }
    }
}