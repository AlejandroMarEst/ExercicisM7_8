package cat.itb.m78.exercices.pokedex

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.triviaIndies.GameScreenTriviaVM
import cat.itb.m78.exercices.triviaIndies.MainMenuTriviaView
import cat.itb.m78.exercices.triviaIndies.ResultScreenTriviaView
import cat.itb.m78.exercices.triviaIndies.SettingsMenuTriviaVM
import cat.itb.m78.exercices.triviaIndies.TriviaScreens
import kotlinx.serialization.Serializable

object PokeScreens{
    @Serializable
    data object MainMenuPokedex
    @Serializable
    data object FullPokedex
    @Serializable
    data object FavPokemon
    @Serializable
    data class PokeInfo(val pokemon : Int)
}

@Composable
fun PokedexNav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PokeScreens.MainMenuPokedex){
        composable<PokeScreens.MainMenuPokedex>{
            // MainMenuTriviaView({ navController.navigate(TriviaScreens.QuestionScreen)}, {navController.navigate(TriviaScreens.SettingsMenu)})
            PokedexMainMenu({ navController.navigate(PokeScreens.FullPokedex)})
        }
        composable<PokeScreens.FullPokedex> {
            PokedexViewmodelView()
        }
    }
}