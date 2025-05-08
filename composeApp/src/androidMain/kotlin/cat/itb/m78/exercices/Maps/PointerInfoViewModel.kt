package cat.itb.m78.exercices.Maps

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.db.database
import com.google.android.gms.maps.model.LatLng

class PointerInfoViewModel(id: Int) : ViewModel() {
    val pointer = database.markersQueries.select(id.toLong()).executeAsOne()
}