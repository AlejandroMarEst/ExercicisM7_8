package cat.itb.m78.exercices.triviaIndies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun GameScreenTriviaVM(ToScore: (Int) -> Unit){
    val viewModel = viewModel{GameScreenViewModelTrivia()}
    GameScreenTriviaView(
        viewModel.questionCounter.value,
        ToScore,
        viewModel.currentQuestion.value,
        viewModel::answer,
        viewModel.settings,
        viewModel.score.value)
}

@Composable
fun GameScreenTriviaView(roundNum: Int,
                         ToScore: (Int)->Unit,
                         currentQuestion: Question,
                         answer: (Int, (Int)->Unit)->Unit,
                         settings: ConfigTrivia,
                         score: Int){
    var timeLeft by remember { mutableStateOf(settings.time) }

    fun resetTime(){
        timeLeft = settings.time
    }

    @Composable
    fun TimeLeft(){
        LaunchedEffect(timeLeft) {
            delay(1.seconds)
            timeLeft--
            if (timeLeft == 0) {
                answer(5, ToScore)
                resetTime()
            }
        }
        Text(timeLeft.toString(), fontSize = 2.em, color = Color.Cyan)
    }

    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("Round "+ roundNum +"/" + settings.rounds,  color = Color.Cyan)
        Text("Score: "+ score,  color = Color.Cyan)
        Spacer(Modifier.height(60.dp))
        Text(currentQuestion.question, color = Color.Cyan)
        Spacer(Modifier.height(10.dp))
        Row {
            Button(onClick = {
                answer(1, ToScore)
                resetTime()
                Modifier.width(80.dp)
            }){
                Text(currentQuestion.answer[0],  color = Color.Blue)
            }
            Button(onClick = {
                answer(2, ToScore)
                resetTime()
                Modifier.width(80.dp)
            }){
                Text(currentQuestion.answer[1],  color = Color.Blue)
            }
        }
        Row {
            if (settings.difficulty >= 2) {
                Button(onClick = {
                    answer(3, ToScore)
                    resetTime()
                    Modifier.width(80.dp)
                }) {
                    Text(currentQuestion.answer[2],  color = Color.Blue)
                }
            }
            if (settings.difficulty == 3) {
                Button(onClick = {
                    answer(4, ToScore)
                    resetTime()
                    Modifier.width(80.dp)
                }) {
                    Text(currentQuestion.answer[3],  color = Color.Blue)
                }
            }
        }
        TimeLeft()
    }
}