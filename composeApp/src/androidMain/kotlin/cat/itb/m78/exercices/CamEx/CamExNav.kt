package cat.itb.m78.exercices.CamEx

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.pokedex.PokeScreens
import kotlinx.serialization.Serializable

object CamNav{
    @Serializable
    data object Permissions
    @Serializable
    data object Camera
    @Serializable
    data class Gallery(val photos: List<String>)
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
            CamExScreen({navController.navigate(CamNav.Gallery(it))})
        }
        composable<CamNav.Gallery> {
            GalleryExScreen({navController.navigate(CamNav.Camera)}, it.toRoute<CamNav.Gallery>().photos)
        }
    }
}