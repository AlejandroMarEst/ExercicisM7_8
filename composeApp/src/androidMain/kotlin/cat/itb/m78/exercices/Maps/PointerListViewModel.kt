package cat.itb.m78.exercices.Maps

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.db.Markers
import cat.itb.m78.exercices.db.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PointerListViewModel : ViewModel(){
    var pointerList = database.markersQueries.selectAll().executeAsList()
}