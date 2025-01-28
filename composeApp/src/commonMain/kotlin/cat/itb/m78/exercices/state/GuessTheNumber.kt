package cat.itb.m78.exercices.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GuessNum(){
    val num = "77"
    var numUser by remember { mutableStateOf("") }
    var messageNum by remember { mutableStateOf("") }
    var intent by remember {mutableStateOf(0)}
    var intentString by remember { mutableStateOf("") }
    var intents by remember { mutableStateOf("Intents: ") }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text("Esdevina el número secret")
        TextField(numUser,
            onValueChange = {
                numUser = it
            })
        Button(onClick = {
            intent++
            intentString = intent.toString()
            messageNum = CheckNum(numUser, num)
        }) {
            Text("Verificar")
        }
        Text(intents + intentString)
        Text(messageNum)
    }
}

fun CheckNum(numUser : String, num : String) : String{
    val gran = "El num que busques es més gran"
    val petit = "El num que busques es més petit"
    val encert = "Has encertat!"
    if(numUser < num){
        return gran
    } else if (numUser > num){
        return petit
    } else {
        return encert
    }
}