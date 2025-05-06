package cat.itb.m78.exercices.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.mutableStateOf
import cat.itb.m78.exercices.db.database
import kotlinx.coroutines.launch

class PokedexViewmodel : ViewModel(){
    val search = mutableStateOf("")
    var pokemonListWithDex = mutableStateOf<MutableList<PokemonWithDex>?>(null)
    val pokeFavs = mutableStateOf<List<PokemonWithFavs>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            val pokeListTemp = mutableListOf<PokemonWithDex>()
            for (pokemon in PokedexApi.list().pokemons){
                pokeListTemp.add(PokemonWithDex(PokedexApi.list().pokemons.indexOf(pokemon) + 1, pokemon.name, pokemon.url))
            }
            pokemonListWithDex.value = pokeListTemp
            loadData()
        }
    }
    fun loadData(){
        viewModelScope.launch(Dispatchers.Default){
            val pokeListTemp = pokemonListWithDex.value
            val pokeFavsList = mutableListOf<PokemonWithFavs>()
            for (pokemon in pokeListTemp!!){
                val fav : Boolean = database.pokemonQueries.selectPoke(pokemon.pokedexNum.toLong()).executeAsOneOrNull() != null
                pokeFavsList.add(PokemonWithFavs(pokemon, fav))
            }
            pokeFavs.value = pokeFavsList.filter { it.pokemon.name.startsWith(search.value, ignoreCase = true) }
        }
    }

    fun toggleFav(pokemonFav: PokemonWithFavs){
        val Pos : Int = pokeFavs.value!!.indexOf(pokemonFav)
        if (pokeFavs.value!![Pos].fav){
            database.pokemonQueries.deletePoke(pokemonFav.pokemon.pokedexNum.toLong())
        } else{
            database.pokemonQueries.insert(pokemonFav.pokemon.pokedexNum.toLong(), pokemonFav.pokemon.name, pokemonFav.pokemon.url)
        }
        loadData()
    }

    fun searchUpdate(string: String){
        search.value = string
        loadData()
    }
}