package cat.itb.m78.exercices.examAPI

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat


class AlumnsViewModel() : ViewModel(){
    val alumns = mutableStateOf<List<Alumns>?>(null)
    val faltaNum = mutableStateOf<MutableList<Int>?>(null)
    init {
        loadData()
    }
    private fun loadData(){
        viewModelScope.launch(Dispatchers.Default) {
            alumns.value = ExamApi.list()
            var listTemp = mutableListOf<Int>()
            for (alumn in alumns.value!!){
                listTemp.add(database.faltesQueries.selectFaltesPerAlumne(alumn.id.toLong()).executeAsList().size)
            }
            faltaNum.value = listTemp
        }
    }
    fun addFalta(id : Int){
        val now = Clock.System.now()
        database.faltesQueries.insert(id.toLong(), now.toString())
        loadData()
    }
}