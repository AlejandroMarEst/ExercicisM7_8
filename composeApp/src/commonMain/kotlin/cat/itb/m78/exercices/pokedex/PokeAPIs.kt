package cat.itb.m78.exercices.pokedex

import cat.itb.m78.exercices.triviaIndies.Questions
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object PokedexApi{
    private val url = "https://pokeapi.co/api/v2/pokemon/?limit=99999"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list(url: String) = client.get(url).body<PokemonInfo>()
    suspend fun list() = client.get(url).body<PokemonList>()
}
