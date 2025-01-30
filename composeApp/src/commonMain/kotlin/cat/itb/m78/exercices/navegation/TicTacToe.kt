package cat.itb.m78.exercices.navegation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.viewModel.updateValue

class ticTacToeViewModel : ViewModel() {
    var board = mutableStateOf(List(3) { List<Boolean?>(3) { null } })
    var player = mutableStateOf(true)
    fun assignShape(num1: Int, num2: Int, playerX: Boolean) {
        if (playerX) {
            val newBoard = board.value.updateValue(num1, num2, true)
            if (board.value[num1][num2] == null) {
                board.value = newBoard
            }
        } else {
            val newBoard = board.value.updateValue(num1, num2, false)
            if (board.value[num1][num2] == null) {
                board.value = newBoard
            }
        }
    }

    fun changePlayer() {
        player.value = !player.value
    }

    fun checkBoard(num1: Int, num2: Int){

    }
}
@Composable
fun TicTacToeResources(){
    val viewModel = viewModel { ticTacToeViewModel() }
    TicTacToe(viewModel.player.value, viewModel.board.value,
              viewModel::assignShape,
              viewModel::changePlayer)
}
@Composable
fun TicTacToe(player : Boolean, board: List<List<Boolean?>>, assignShape: (Int, Int, Boolean) -> Unit, changePlayer: () -> Unit){
    Column {
        for (i in 0..board.size - 1) {
            Row {
                for (j in 0..board.size - 1) {
                    Box(modifier = Modifier.size(100.dp)
                        .border(width = 2.dp, color = Gray)
                        .background(Color.LightGray, shape = RoundedCornerShape(20.dp))) {
                        TextButton(
                            onClick = { assignShape(i, j, player); changePlayer()}, modifier = Modifier.fillMaxSize()
                        ) {
                            if (board[i][j] == true){
                                Text("X", fontSize = 5.em)
                            } else if (board[i][j] == false){
                                Text("O", fontSize = 5.em)
                            } else {
                                Text("")
                            }
                        }
                    }
                }
            }
        }
    }
}