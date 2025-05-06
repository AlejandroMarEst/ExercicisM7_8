package cat.itb.m78.exercices.Maps

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.db.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PointerListViewModel : ViewModel(){
    var pointerList = mutableStateOf<MutableList<String>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            var temp = database.markersQueries.selectAll().executeAsList()
            var listTemp = mutableListOf<String>()
            for (item in temp){
                listTemp.add(item.Name)
            }
            pointerList.value = listTemp
        }
    }
}