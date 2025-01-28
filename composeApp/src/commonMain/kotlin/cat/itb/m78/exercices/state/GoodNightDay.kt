package cat.itb.m78.exercices.state

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em

@Composable
fun GoodMornin(){
    Column {
        var text = remember { mutableStateOf("Good ?!") }
        Text(text.value)
        Button(onClick = {
            text.value = "Good Morning!"
        }) {
            Text("Morning")
        }
        Button(onClick = {
            text.value = "Good Night!"
        }) {
            Text("Night")
        }
    }
}