package cat.itb.m78.exercices.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.MyApi
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.mutableStateOf
import cat.itb.m78.exercices.bd.database
import kotlinx.coroutines.launch

class PokedexViewmodel : ViewModel(){
    var pokemonList = mutableStateOf<List<Pokemon>?>(null)
    val pokeFavs = mutableStateOf<List<PokemonWithFavs>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            pokemonList.value = PokedexApi.list().pokemons
        }
    }
    fun loadData(){
        viewModelScope.launch(Dispatchers.Default){
            val apiList = PokedexApi.list().pokemons
            val pokeFavsList = mutableListOf<PokemonWithFavs>()
            for (pokemon in apiList){
                val fav : Boolean = database.pokemonQueries.selectPoke(pokemon.numPokedex.toLong()).executeAsOneOrNull() != null
                pokeFavsList.add(PokemonWithFavs(pokemon, fav))
            }
            pokeFavs.value = pokeFavsList
        }
    }
    fun toggleFav(pokemon: Pokemon){

    }
}