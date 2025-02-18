package cat.itb.m78.exercices.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.triviaIndies.GameScreenViewModelTrivia

@Composable
fun calculatorView(toEnd : (Int) -> Unit){
    val viewModel = viewModel{ CalculatorViewModel() }
    calculator(viewModel.result.value, viewModel.operation.value, viewModel::changeOperation, viewModel::doOperation, toEnd)
}


@Composable
fun calculator(calculate : Int, operation : Int,changeOperation:(Int) -> Unit, doOperation:(Int?) -> Unit, toEnd: (Int) -> Unit) {
    val numUser = remember { mutableStateOf("") }
    fun calculate(){
        doOperation(numUser.value.toIntOrNull())
        numUser.value = ""
    }
    Column (Modifier.fillMaxSize().background(Color.Yellow), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(calculate.toString(), fontSize = 3.em)
            Column(Modifier.clip(RoundedCornerShape(15.dp)).background(Color.DarkGray).padding(10.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                Row {
                    Button(onClick = {
                        changeOperation(1)
                    }) {
                        if (operation == 1) {
                            Text("+", fontWeight = FontWeight.Bold)
                        } else {
                            Text("+")
                        }
                    }
                    Button(onClick = {
                        changeOperation(2)
                    }) {
                        if (operation == 2) {
                            Text("-", fontWeight = FontWeight.Bold)
                        } else {
                            Text("-")
                        }
                    }
                    Button(onClick = {
                        changeOperation(3)
                    }) {
                        if (operation == 3) {
                            Text("*", fontWeight = FontWeight.Bold)
                        } else {
                            Text("*")
                        }
                    }
                    Button(onClick = {
                        changeOperation(4)
                    }) {
                        if (operation == 4) {
                            Text("/", fontWeight = FontWeight.Bold)
                        } else {
                            Text("/")
                        }
                    }
                }
                TextField(
                    value = numUser.value,
                    onValueChange = { numUser.value = it },
                    Modifier.clip(RoundedCornerShape(15.dp)).background(Color.DarkGray)
                )
                Row {
                    Button(onClick = {
                        toEnd(calculate)
                    }) {
                        Text("End")
                    }
                    Button(onClick = {
                        calculate()
                    }) {
                        Text("Calculate")
                    }
                }
            }

    }
}