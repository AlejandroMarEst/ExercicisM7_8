package cat.itb.m78.exercices.triviaIndies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreenTriviaView(score : Int, ToMainMenu: () -> Unit){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("Good job!",  color = Color.Cyan)
        Text("Your score is: " + score.toString(),  color = Color.Cyan)
        Spacer(Modifier.height(50.dp))
        TextButton(
            onClick = { ToMainMenu() }
        ) {
            Text("Return to main menu",  color = Color.Cyan)
        }
    }
}