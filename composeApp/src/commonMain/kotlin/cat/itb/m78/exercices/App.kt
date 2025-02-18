package cat.itb.m78.exercices


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.navegation.MainMenu
import cat.itb.m78.exercices.navegation.NavegationResources
import cat.itb.m78.exercices.navegation.StartMenu
import cat.itb.m78.exercices.navegation.StartingMenu
import cat.itb.m78.exercices.navegation.TicTacToeResources
import cat.itb.m78.exercices.navegation.WinScreen
import cat.itb.m78.exercices.navegation.menuNav
import cat.itb.m78.exercices.state.Gambling
import cat.itb.m78.exercices.state.GoodMornin
import cat.itb.m78.exercices.state.GuessNum
import cat.itb.m78.exercices.state.Hello
import cat.itb.m78.exercices.stateless.Contact
import cat.itb.m78.exercices.stateless.HelloWorld
import cat.itb.m78.exercices.stateless.MessagesList
import cat.itb.m78.exercices.stateless.Resources
import cat.itb.m78.exercices.stateless.Welcome
import cat.itb.m78.exercices.theme.AppTheme
import cat.itb.m78.exercices.viewModel.Counter
import cat.itb.m78.exercices.viewModel.CounterResources
import cat.itb.m78.exercices.viewModel.OteloResources
import cat.itb.m78.exercices.viewModel.ShoppingResources
import kotlinx.serialization.Serializable
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
internal fun App() = AppTheme {
    StartingMenu()
}