package cat.itb.m78.exercices.examAPI

import cat.itb.m78.exercices.pokedex.PokemonInfo
import cat.itb.m78.exercices.pokedex.PokemonList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ExamApi{
    private val url = "https://fp.mateuyabar.com/DAM-M78/composeP2/exam/students.json"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list(url: String) = client.get(url).body<Alumns>()
    suspend fun list() = client.get(url).body<List<Alumns>?>()
}