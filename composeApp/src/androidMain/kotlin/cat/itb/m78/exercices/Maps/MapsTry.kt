package cat.itb.m78.exercices.Maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.GoogleMap
import androidx.compose.ui.Modifier

@Composable
fun MapsScreen(ToPointers:()->Unit){
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    )
}