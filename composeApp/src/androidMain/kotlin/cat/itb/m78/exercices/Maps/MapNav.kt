package cat.itb.m78.exercices.Maps

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.CamEx.CamExScreen
import cat.itb.m78.exercices.CamEx.CamNav
import cat.itb.m78.exercices.CamEx.FeatureThatRequiresCameraPermission
import cat.itb.m78.exercices.CamEx.GalleryExScreen
import kotlinx.serialization.Serializable

object MapNav{
    @Serializable
    data object Map
    @Serializable
    data object Pointers
    @Serializable
    data object PointerCreater
}

@Composable
fun MapNav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MapNav.Map){
        composable<MapNav.Map>{
            //PokedexMainMenu({ navController.navigate(PokeScreens.FullPokedex)})
            MapsScreen({navController.navigate(MapNav.Pointers)})
        }
        composable<CamNav.Camera> {
            CamExScreen({navController.navigate(CamNav.Gallery(it))})
        }
        composable<CamNav.Gallery> {
            GalleryExScreen({navController.navigate(CamNav.Camera)}, it.toRoute<CamNav.Gallery>().photos)
        }
    }
}