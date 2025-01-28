package cat.itb.m78.exercices.viewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.absoluteValue

data class Item(val name: String, val quantity : String)
class shoppingViewModel : ViewModel() {
    val name = mutableStateOf("")
    val count = mutableStateOf(0)
    val items = mutableStateOf(0)
    val lista = mutableStateOf(listOf<Item>())
    fun assingName(text : String){
        name.value = text
    }
    fun assingNum(num : String){
        count.value = num.toInt()
    }
    fun assignLista(){
        lista.value += (Item(name.value, count.value.toString()))
    }

}
@Composable
fun ShoppingResources(){
    val viewModel = viewModel { shoppingViewModel() }
    ShopphingList(viewModel.name.value,
                  viewModel.count.value,
                  viewModel.lista.value,
                  viewModel::assingName,
                  viewModel::assingNum,
                  viewModel::assignLista
        )
}
@Composable
fun ShopphingList(name : String, num : Int, lista: List<Item>,
                  assingName: (String) -> Unit,
                  assingNum: (String) -> Unit,
                  assingLista: () -> Unit){
    Column( horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(20.dp))
        Card {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    name,
                    onValueChange = { assingName(it) },
                    label = { Text("Name") })
                TextField(
                    num.toString(),
                    onValueChange = { assingNum(it) },
                    label = { Text("Amount") })
                Button(
                    onClick = { assingLista() }
                ) {
                    Text("Create")
                }
            }
        }
        LazyColumn(modifier = Modifier.padding(10.dp)){
            items(lista.size) { message ->
                Card(modifier = Modifier.padding(5.dp).width(500.dp)){
                    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(lista[message].name, textAlign = TextAlign.Left)
                        Text(lista[message].quantity, textAlign = TextAlign.Right)
                    }
                }
            }
        }
    }
}