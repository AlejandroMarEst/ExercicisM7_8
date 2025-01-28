package cat.itb.m78.exercices.viewModel

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class counterViewModel : ViewModel() {
    val counter1 = mutableStateOf(0)
    val counter2 = mutableStateOf(0)
    fun counter1Rise(){
        counter1.value++
    }
    fun counter2Rise(){
        counter2.value++
    }
    fun counterReset(){
        counter1.value = 0
        counter2.value = 0
    }
}
@Composable
fun CounterResources(){
    val viewModel = viewModel { counterViewModel() }
    Counter(viewModel.counter1.value.toString(),
            viewModel.counter2.value.toString(),
            viewModel::counter1Rise,
            viewModel::counter2Rise,
            viewModel::counterReset)
}
@Composable
fun Counter(counter1 : String, counter2 : String, counter1Rise: ()-> Unit, counter2Rise: ()-> Unit, counterReset: ()-> Unit) {

    Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
    Box(modifier = Modifier.size(250.dp)
        .border(width = 4.dp, color = Gray, shape = RoundedCornerShape(10.dp))
        .background(Color.LightGray, shape = RoundedCornerShape(10.dp)))
    {
        Column(modifier = Modifier.padding(25.dp, 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Text(counter1, modifier = Modifier.padding(40.dp, 10.dp))
                Text(counter2, modifier = Modifier.padding(40.dp, 10.dp))
            }
            Row {
                Button(
                    onClick = counter1Rise, modifier = Modifier.padding(5.dp)
                ) {
                    Text("Score")
                }
                Button(
                    onClick = counter2Rise, modifier = Modifier.padding(5.dp)
                ) {
                    Text("Score")
                }
            }
            Button(
                onClick = counterReset, modifier = Modifier.padding(5.dp)
            ) {
                Text("Reset")
            }
        }
    }
}
}
