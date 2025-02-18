package cat.itb.m78.exercices.triviaIndies

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

object TriviaScreens{
    @Serializable
    data object StartingMenu
    @Serializable
    data object QuestionScreen
    @Serializable
    data class ScoreScreen(val score : Int)
    @Serializable
    data object SettingsMenu
}

@Composable
fun TriviaNav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TriviaScreens.StartingMenu){
        composable<TriviaScreens.StartingMenu>{
            MainMenuTriviaView({ navController.navigate(TriviaScreens.QuestionScreen)}, {navController.navigate(TriviaScreens.SettingsMenu)})
        }
        composable<TriviaScreens.QuestionScreen>{
            GameScreenTriviaVM({ navController.navigate(TriviaScreens.ScoreScreen(it))})
        }
        composable<TriviaScreens.SettingsMenu>{
            SettingsMenuTriviaVM({ navController.navigate(TriviaScreens.StartingMenu) })
        }
        composable<TriviaScreens.ScoreScreen> {
            ResultScreenTriviaView( it.toRoute<TriviaScreens.ScoreScreen>().score, {navController.navigate(TriviaScreens.StartingMenu)})
        }
    }
}