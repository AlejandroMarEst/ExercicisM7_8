package cat.itb.m78.exercices.Maps

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.db.database
import com.google.android.gms.maps.model.LatLng
import com.russhwolf.settings.Settings

class CreateMarkerViewModel(lat: Double, lon: Double, private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val coords = LatLng(lat, lon)
    var usingLastPhoto = mutableStateOf(false)
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val photoUri = savedStateHandle.getStateFlow<String?>("PHOTO_URI_KEY", null)
    val settings: Settings = Settings()
    var lastPhoto: String? = settings.getStringOrNull("lastPhoto")
    fun updateLastPhoto(){
        lastPhoto = settings.getStringOrNull("lastPhoto")
    }
    fun switchUsingLastPhoto(){
        usingLastPhoto.value = !usingLastPhoto.value
    }
    fun titleChange(it : String){
        title.value = it
    }
    fun descriptionChange(it: String){
        description.value = it
    }
    fun addMarker(navigateToMapParam:()->Unit, usingLastPhoto : Boolean){
        if (usingLastPhoto && lastPhoto != null){
            database.markersQueries.insert(title.value, description.value,
                lastPhoto!!, coords.latitude, coords.longitude)
        } else {
            database.markersQueries.insert(title.value, description.value,
                photoUri.value.toString(), coords.latitude, coords.longitude)
        }
        navigateToMapParam()
    }
}

