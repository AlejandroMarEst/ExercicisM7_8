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
import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.Serializable

object MapNav{
    @Serializable
    data object Map
    @Serializable
    data object Pointers
    @Serializable
    data class PointerCreator(val pointerCoords : Float, val pointerCoords2: Float)
}

@Composable
fun MapNav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MapNav.Map){
        composable<MapNav.Map>{
            //PokedexMainMenu({ navController.navigate(PokeScreens.FullPokedex)})
            MapsScreen( {navController.navigate(MapNav.Pointers)}, {pointerCoords, pointerCoords2 -> navController.navigate(MapNav.PointerCreator(pointerCoords, pointerCoords2))})
        }
        composable<MapNav.Pointers> {
            PointerList({navController.navigate(MapNav.Map)})
        }
        composable<MapNav.PointerCreator> {
            PointerCreator({navController.navigate(CamNav.Camera)}, it.toRoute<MapNav.PointerCreator>().pointerCoords, it.toRoute<MapNav.PointerCreator>().pointerCoords2)
        }
    }
}