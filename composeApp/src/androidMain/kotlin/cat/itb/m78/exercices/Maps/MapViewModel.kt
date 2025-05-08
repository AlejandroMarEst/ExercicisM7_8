package cat.itb.m78.exercices.Maps

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.db.database
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Marker(val id: Long, val coords: LatLng, val title: String)

class MapViewModel : ViewModel() {
    val startingLocation = LatLng(41.4529053, 2.1859949)
    val markers = mutableStateOf(updateMarkers())
    private fun updateMarkers(): List<Marker>{
        val markersUpdated = mutableListOf<Marker>()
        viewModelScope.launch(Dispatchers.Default) {
            for(i in database.markersQueries.selectAll().executeAsList()){
                markersUpdated += Marker(i.Id, LatLng(i.Lat, i.Long), i.Name)
            }
        }
        return markersUpdated
    }
}