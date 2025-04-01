package cat.itb.m78.exercices.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.MyApi
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.launch

class PokedexViewmodel : ViewModel(){
    var pokemonList = mutableStateOf<List<Pokemon>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            pokemonList.value = PokedexApi.list().pokemons
        }
    }
}