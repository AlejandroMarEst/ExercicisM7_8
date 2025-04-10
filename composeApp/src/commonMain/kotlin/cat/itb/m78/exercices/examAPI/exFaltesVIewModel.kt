package cat.itb.m78.exercices.examAPI

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FaltesViewModel() : ViewModel() {
    val faltesWithName = mutableStateOf<MutableList<FaltaName>?>(null)
    init {
        loadData()
    }
    private fun loadData(){
        viewModelScope.launch(Dispatchers.Default) {
            val temp = database.faltesQueries.selectAll().executeAsList()
            val alumns = ExamApi.list()
            val tempList = mutableListOf<FaltaBBDD>()
            val tempListNames = mutableListOf<FaltaName>()
            for (reg in temp){
                tempList.add(FaltaBBDD(reg.id_alumne.toInt(), reg.date))
                tempListNames.add(FaltaName(alumns!![reg.id_alumne.toInt()-1].name, reg.date))
            }
            faltesWithName.value = tempListNames
        }
    }
}