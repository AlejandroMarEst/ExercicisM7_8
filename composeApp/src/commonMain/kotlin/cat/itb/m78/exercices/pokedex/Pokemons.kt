package cat.itb.m78.exercices.pokedex

import io.github.koalaplot.core.bar.Slot
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import m78exercices.composeapp.generated.resources.Res

@Serializable
data class PokemonList(
    @SerialName("results") val pokemons : List<Pokemon>
)

@Serializable
data class Pokemon(
    val name : String,
    val url : String
)

@Serializable
data class PokemonWithDex(
    val pokedexNum : Int,
    val name: String,
    val url: String
)

@Serializable
data class PokemonWithFavs(
    val pokemon: PokemonWithDex,
    var fav: Boolean
)

@Serializable
data class PokemonInfo(
    @SerialName("id") val numPokedex : Int,
    val name : String,
    val sprites : Sprites,
    private val types : List<TypeInfo?>,
    val height : Int,
    val weight : Int
){
    val primaryType get() = types[0]
    val secondaryType get() = if (types.size>1){types[1]} else { null }
}

@Serializable
data class Sprites(
    @SerialName("front_default") val frontDefaultMasc : String,
    @SerialName("back_default") val backDefaultMasc : String? = null,
    @SerialName("front_shiny") val frontShinyMasc : String,
    @SerialName("back_shiny") val backShinyMasc : String? = null,
    @SerialName("front_female") val frontDefaultFem : String? = null,
    @SerialName("back_female") val backDefaultFem : String? = null,
    @SerialName("front_shiny_femal") val frontShinyFem : String? = null,
    @SerialName("back_shiny_female") val backShinyFem : String? = null
)

@Serializable
data class TypeInfo(
    val slot : Int,
    val type : Type
)

@Serializable
data class Type(
    val name: String,
    val url: String
)