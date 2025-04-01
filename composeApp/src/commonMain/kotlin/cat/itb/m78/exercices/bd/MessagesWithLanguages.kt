package cat.itb.m78.exercices.bd

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MessagesWithLanguagesViewModel : ViewModel(){
    private val messagesQueries = database.messagesQueries
    val messages = mutableStateOf(messagesQueries.selectAll().executeAsList())
    val txt = mutableStateOf("")
    fun addMsg(){
        database.messagesQueries.insert(txt.value)
        messages.value += txt.value
    }
    fun assignTxt(text : String){
        txt.value = text
    }
}

@Composable
fun MessagesWithLanguagesApp(){
    val viewModel = viewModel{ MessagesWithLanguagesViewModel() }
    MessagesWithLanguagesView(viewModel.messages.value, viewModel.txt.value, viewModel::addMsg, viewModel::assignTxt)
}

@Composable
fun MessagesWithLanguagesView(
    Msgs: List<String>,
    txt: String,
    addMsg: () -> Unit,
    assignTxt: (String) -> Unit
){
    Column {
        TextField(
            value = txt,
            onValueChange = assignTxt,
            label = { Text("Write here") })
        Button(onClick = addMsg ) {
            Text("Add message")
        }
        Msgs.forEach { msg -> Text(msg)}
    }
}