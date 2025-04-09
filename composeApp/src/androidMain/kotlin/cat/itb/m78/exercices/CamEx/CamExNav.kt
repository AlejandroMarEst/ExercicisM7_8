package cat.itb.m78.exercices.CamEx

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

object CamNav{
    @Serializable
    data object Permissions
    @Serializable
    data object Camera
    @Serializable
    data object Gallery
}

@Composable
fun CamExNav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CamNav.Permissions){
        composable<CamNav.Permissions>{
            //PokedexMainMenu({ navController.navigate(PokeScreens.FullPokedex)})
            FeatureThatRequiresCameraPermission({navController.navigate(CamNav.Camera)})
        }
        composable<CamNav.Camera> {
            CamExScreen()
        }
        composable<CamNav.Gallery> {

        }
    }
}