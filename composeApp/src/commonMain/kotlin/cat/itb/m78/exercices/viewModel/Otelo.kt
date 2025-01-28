package cat.itb.m78.exercices.viewModel

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class oteloViewModel : ViewModel() {
    var board = mutableStateOf( List(10){ List<Boolean?>(10){null}})
    fun assignColor(Num1 : Int, Num2 : Int, Player : Boolean){
        if(Player){
            val newBoard = board.value.updateValue(Num1,Num2,true)
            board.value = newBoard;
        }
        else {
            val newBoard = board.value.updateValue(Num1,Num2,false)
            board.value = newBoard;
        }
    }
}

fun <T> List<List<T?>>.updateValue(i: Int, j: Int, value: T) : List<List<T?>>{
    val mutableBoard = map { it.toMutableList() }
    mutableBoard[i][j]=value
    return mutableBoard
}

@Composable
fun OteloResources(){
    val viewModel = viewModel { oteloViewModel() }
    Otelo(viewModel.board.value,
          viewModel::assignColor)
}
@Composable
fun Otelo(board: List<List<Boolean?>>, assignColor: (Int, Int, Boolean) -> Unit) {
    Column {
        for (i in 1..board.size - 1) {
            Row {
                for (j in 1..board.size - 1) {
                    Box(modifier = Modifier.size(50.dp)
                        .border(width = 4.dp, color = Gray, shape = RoundedCornerShape(10.dp))
                        .background(Color.LightGray, shape = RoundedCornerShape(10.dp))) {
                        Button(
                            onClick = { assignColor(i, j, true) }, modifier = Modifier.padding(5.dp)
                        ) {
                            Text(board[i][j].toString())
                        }
                    }
                }
            }
        }
    }
}