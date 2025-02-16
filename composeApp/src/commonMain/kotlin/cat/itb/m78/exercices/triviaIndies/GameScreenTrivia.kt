package cat.itb.m78.exercices.triviaIndies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun GameScreenTriviaView(roundNum: Int,
                         ToScore: (Int)->Unit,
                         currentQuestion: Question,
                         answer: (Int, (Int)->Unit)->Unit,
                         settings: ConfigTrivia,
                         score: Int){
    var timeLeft by remember { mutableStateOf(settings.time) }

    @Composable
    fun TimeLeft(){
        LaunchedEffect(timeLeft) {
            delay(1.seconds)
            timeLeft--
            if (timeLeft == 0) {
                answer(5, ToScore)
            }
        }
        Text(timeLeft.toString(), fontSize = 2.em)
    }

    fun resetTime(){
        timeLeft = settings.time
    }



    Column {
        Text("Round 1/10")
        Spacer(Modifier.height(60.dp))
        Text("Question")
        Spacer(Modifier.height(10.dp))
        Row {
            Button(onClick = {}){
                Text("Return to menu")
            }
            Button(onClick = {}){
                Text("Return to menu")
            }
        }
        Row {
            Button(onClick = {}){
                Text("Return to menu")
            }
            Button(onClick = {}){
                Text("Return to menu")
            }
        }
    }
}