package cat.itb.m78.exercices.calculator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.absoluteValue

class CalculatorViewModel : ViewModel(){
    val result = mutableStateOf(0)
    val operation = mutableStateOf(1)
    fun changeOperation(newOperation : Int){
        operation.value = newOperation
    }
    fun doOperation(num : Int?){
        if (num!= null) {
            when (operation.value) {
                1 -> result.value += num
                2 -> result.value -= num
                3 -> result.value *= num
                4 -> result.value /= num
            }
        }
    }
}