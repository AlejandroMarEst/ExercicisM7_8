package cat.itb.m78.exercices.Maps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun Map(navigateToCreatePointer: (Double, Double)->Unit){
    val viewModel = viewModel{MapViewModel()}
    MapsScreen(
        viewModel.startingLocation,
        navigateToCreatePointer,
        viewModel.markers.value
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapsScreen(
    initialZone: LatLng,
    navigateToCreatePointer: (Double, Double) -> Unit,
    pointers: List<Marker>
) {
    val cameraPositionState = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(initialZone, 15f)
    }
    Box{
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = {coords-> navigateToCreatePointer(coords.latitude, coords.longitude)}
        ){
            for(i in pointers){
                AdvancedMarker(
                    state = MarkerState(position = i.coords),
                    title = i.title
                )
            }
        }
    }
}