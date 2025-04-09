package cat.itb.m78.exercices.pokedex

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokeFullInfoViewModel(poke : Int) : ViewModel() {
    val pokemon = mutableStateOf<PokemonInfo?>(null)
    val shiny = mutableStateOf(false)
    val male = mutableStateOf(true)
    private val poke = poke
    private val url = "https://pokeapi.co/api/v2/pokemon/"
    init {
        loadData(poke)
    }
    private fun loadData(int : Int) {
        viewModelScope.launch(Dispatchers.Default) {
            pokemon.value = PokedexApi.list(url + "\\" + int.toString() + "\\")
        }
    }
    fun toggleShiny(){
        shiny.value != shiny.value
        loadData(poke)
    }
    fun toggleMale(){
        male.value != male.value
        loadData(poke)
    }
}