package cat.itb.m78.exercices.triviaIndies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsMenuTriviaVM(ToMenu: () -> Unit){
    val viewModel = viewModel{SettingsMenuViewModelTrivia()}
    SettingsMenuTriviaView(viewModel.difficulty.value,
                           viewModel.rounds.value,
                           viewModel.time.value,
                           viewModel::setRounds,
                           viewModel::setDifficulty,
                           viewModel::setTime,
                           ToMenu,
                           viewModel::exitSettings)
}

@Composable
fun SettingsMenuTriviaView(difficulty: Int,
                           round: Int,
                           time: Int,
                           setRounds: (Int)->Unit,
                           setDifficulty: (Int)->Unit,
                           setTime: (Int)->Unit,
                           ToMenu: () -> Unit,
                           exitSettings: (() -> Unit) -> Unit) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Row {
            Text("Difficulty")
            Spacer(Modifier.width(20.dp))
            Text("1")
            RadioButton(
                selected = (difficulty == 1),
                onClick = { setDifficulty(1) }
            )
            Text("2")
            RadioButton(
                selected = (difficulty == 2),
                onClick = { setDifficulty(2) }
            )
            Text("3")
            RadioButton(
                selected = (difficulty == 3),
                onClick = { setDifficulty(3) }
            )
        }
        Row {
            Text("Rounds")
            Spacer(Modifier.width(20.dp))
            Text("5")
            RadioButton(
                selected = (round == 5),
                onClick = { setRounds(5) }
            )
            Text("10")
            RadioButton(
                selected = (round == 10),
                onClick = { setRounds(10) }
            )
            Text("15")
            RadioButton(
                selected = (round == 15),
                onClick = { setRounds(15) }
            )
        }
        Row {
            Text("Time per round")
            Spacer(Modifier.width(20.dp))
            Text("5")
            RadioButton(
                selected = (time == 5),
                onClick = { setTime(5) }
            )
            Text("10")
            RadioButton(
                selected = (time == 10),
                onClick = { setTime(10) }
            )
            Text("15")
            RadioButton(
                selected = (time == 15),
                onClick = { setTime(15) }
            )
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = { exitSettings(ToMenu) }) {
            Text("Return to menu")
        }
    }
}