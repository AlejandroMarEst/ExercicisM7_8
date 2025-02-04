package cat.itb.m78.exercices.navegation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.stateless.HelloWorld
import cat.itb.m78.exercices.viewModel.updateValue
import kotlinx.serialization.Serializable

object menuNav {
    @Serializable
    data object StartMenu
    @Serializable
    data object GameBoard
    @Serializable
    data class WinScreen(val playerX: Boolean)
}
@Composable
fun StartMenu() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = menuNav.StartMenu) {
        composable<menuNav.StartMenu> {
            StartMenu(
                { navController.navigate(menuNav.GameBoard) }
            )
        }
        composable<menuNav.GameBoard> { TicTacToeResources({ navController.navigate(menuNav.WinScreen(it)) }) }
        composable<menuNav.WinScreen> { backStack -> val playerX = backStack.toRoute<menuNav.WinScreen>().playerX; WinScreen(playerX) }
    }
}
@Composable
fun StartMenu(GameBoard: () -> Unit){
    Column {
        Text("Tic Tac Toe")
        Button(
            onClick = GameBoard, modifier = Modifier.padding(5.dp)
        ) {
            Text("Start")
        }
    }
}
@Composable
fun WinScreen(playerX: Boolean){
    if (playerX){
        Text("Han guanyat les X's")
    } else {
        Text("Han guanyat les O's")
    }
}
class ticTacToeViewModel : ViewModel() {
    var board = mutableStateOf(List(3) { List<Boolean?>(3) { null } })
    var player = mutableStateOf(true)
    var win = mutableStateOf(false)
    fun playAt(num1: Int, num2: Int){
        if (!win.value) {
            assignShape(num1, num2)
            changePlayer()
            checkBoard()
        }
    }
    fun assignShape(num1: Int, num2: Int) {
        if (player.value) {
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

    fun checkBoard(){
        val board = board.value
        var winCheck = false

        for(i in 0..2){
            winCheck = winCheck || board[i][0]==board[i][1]&&board[i][1]==board[i][2]&&board[i][0]!=null
            winCheck = winCheck || board[0][i]==board[1][i]&&board[1][i]==board[2][i]&&board[0][i]!=null
        }
        winCheck = winCheck || board[0][0]==board[1][1]&&board[1][1]==board[2][2]&&board[0][0]!=null
        winCheck = winCheck || board[2][0]==board[1][1]&&board[1][1]==board[0][2]&&board[2][0]!=null
        if (winCheck) { win.value = true}
    }
}
@Composable
fun TicTacToeResources(winScreen: (Boolean) -> Unit){
    val viewModel = viewModel { ticTacToeViewModel() }
    TicTacToe(viewModel.win.value,viewModel.player.value, viewModel.board.value,
              viewModel::playAt, winScreen)
}
@Composable
fun TicTacToe(win : Boolean, player : Boolean, board: List<List<Boolean?>>, play: (Int, Int) -> Unit, winScreen: (Boolean) -> Unit){
    Column {
        if (win && player){
            winScreen(player)
        } else if (win && !player){
            winScreen(!player)
        }
        for (i in 0..board.size - 1) {
            Row {
                for (j in 0..board.size - 1) {
                    Box(modifier = Modifier.size(100.dp)
                        .border(width = 2.dp, color = Gray)
                        .background(Color.LightGray, shape = RoundedCornerShape(20.dp))) {
                        TextButton(
                            onClick = { play(i, j)}, modifier = Modifier.fillMaxSize()
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