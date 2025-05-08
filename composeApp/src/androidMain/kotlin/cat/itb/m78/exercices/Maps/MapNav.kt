package cat.itb.m78.exercices.Maps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

object MapNav{
    @Serializable
    data object Map
    @Serializable
    data object Pointers
    @Serializable
    data class PointerCreator(val lat : Float, val lon: Float)
    @Serializable
    data object Camera
    @Serializable
    data object Permissions
    @Serializable
    data class PointerInfo(val id : Int)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapNav(){
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Date marker",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = { Text("Map", style = MaterialTheme.typography.bodyLarge) },
                        selected = false,
                        onClick = { navController.navigate(MapNav.Map) }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Location List",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        selected = false,
                        onClick = { navController.navigate(MapNav.Pointers) }
                    )
                }
            }
        },
        drawerState = drawerState,
        gesturesEnabled =  !drawerState.isClosed
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text("CamMap")},
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if(drawerState.isClosed){
                                    drawerState.open()
                                }else{
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    }
                )
            }
        ) { contentPadding->
            Column(Modifier.padding(contentPadding)){
                NavHost(navController = navController, startDestination = MapNav.Permissions) {
                    composable<MapNav.Permissions> {
                        FeatureThatRequiresCameraPermission{navController.navigate(MapNav.Map)}
                    }
                    composable<MapNav.Map> {
                        Map({
                                lat, lon ->
                            val destination = MapNav.PointerCreator(lat.toFloat(), lon.toFloat())
                            navController.navigate(destination)
                        }
                        )
                    }
                    composable<MapNav.Pointers> {
                        PointerList({navController.navigate(MapNav.PointerInfo(it))})
                    }
                    composable<MapNav.PointerCreator> { navBackStackEntry ->
                        val args = navBackStackEntry.toRoute<MapNav.PointerCreator>()
                        val savedStateHandle = navBackStackEntry.savedStateHandle
                        CreateMarker(
                            args.lat.toDouble(),
                            args.lon.toDouble(),
                            savedStateHandle,
                            { navController.navigate(MapNav.Map) },
                            { navController.navigate(MapNav.Camera) }
                        )
                    }
                    composable<MapNav.Camera>{
                        CameraScreen(
                            onPhotoCaptured = { savedUri ->
                                savedUri?.let { uri ->
                                    navController.previousBackStackEntry ?.savedStateHandle ?.set("PHOTO_URI_KEY", savedUri.toString())
                                    navController.popBackStack()
                                }
                            }
                        )
                    }
                    composable<MapNav.PointerInfo>{
                        PointerInfo(
                            it.toRoute<MapNav.PointerInfo>().id
                        )
                    }
                }
            }
        }
    }
}