package cat.itb.m78.exercices.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.mutableStateOf
import cat.itb.m78.exercices.db.database
import kotlinx.coroutines.launch

class PokefavsViewmodel : ViewModel(){
    val search = mutableStateOf("")
    var pokemonListWithDex = mutableStateOf<MutableList<PokemonWithDex>?>(null)
    val pokeFavs = mutableStateOf<List<PokemonWithFavs>?>(null)
    init {
        loadData()
    }
    fun loadData(){
        viewModelScope.launch(Dispatchers.Default){
            val pokeListTemp = database.pokemonQueries.selectAll().executeAsList().filter{ it.name.startsWith(search.value, ignoreCase = true) }
            val pokeFavsList = mutableListOf<PokemonWithFavs>()
            for (pokemon in pokeListTemp!!){
                println(pokemon.toString()) // Si s'elimina no funciona, no se perque
                pokeFavsList.add(PokemonWithFavs(PokemonWithDex(pokemon.numPokedex.toInt(), pokemon.name, pokemon.url), true))
            }
            pokeFavs.value = pokeFavsList
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