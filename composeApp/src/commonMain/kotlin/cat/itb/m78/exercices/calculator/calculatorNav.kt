package cat.itb.m78.exercices.calculator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.triviaIndies.MainMenuTriviaView
import cat.itb.m78.exercices.triviaIndies.TriviaScreens
import kotlinx.serialization.Serializable

object calculatorScreens{
    @Serializable
    data object calculatorProgram
    @Serializable
    data class calculatorEnd(val score : Int)
}

@Composable
fun calculatorNav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = calculatorScreens.calculatorProgram) {
        composable<calculatorScreens.calculatorProgram>{
            calculatorView({ navController.navigate(calculatorScreens.calculatorEnd( it ))} )
        }
        composable<calculatorScreens.calculatorEnd> {
            calculatorEnd(it.toRoute<calculatorScreens.calculatorEnd>().score)
        }
    }
}