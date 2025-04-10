package cat.itb.m78.exercices.examAPI

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.pokedex.PokeInfoViewmodelView
import cat.itb.m78.exercices.pokedex.PokedexMainMenu
import cat.itb.m78.exercices.pokedex.PokedexViewmodelView
import cat.itb.m78.exercices.pokedex.PokefavsViewmodelView
import kotlinx.serialization.Serializable

object ExScreens{
    @Serializable
    data object Alumns
    @Serializable
    data object Faltas
}

@Composable
fun ExamNav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ExScreens.Alumns){
        composable<ExScreens.Alumns>{
            // MainMenuTriviaView({ navController.navigate(TriviaScreens.QuestionScreen)}, {navController.navigate(TriviaScreens.SettingsMenu)})
            AlumnsViewmodelView( {navController.navigate(ExScreens.Faltas)})
        }
        composable<ExScreens.Faltas> {
            FaltesVIewModelView({navController.navigate(ExScreens.Alumns)})
        }
    }
}