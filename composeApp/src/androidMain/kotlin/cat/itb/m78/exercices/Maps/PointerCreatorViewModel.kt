package cat.itb.m78.exercices.Maps

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PointerCreatorViewModel : ViewModel(){
    val Name = mutableStateOf("")
    val Description = mutableStateOf("")
}