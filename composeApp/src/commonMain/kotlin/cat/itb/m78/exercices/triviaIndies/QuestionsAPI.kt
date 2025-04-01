package cat.itb.m78.exercices.triviaIndies

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object QuestionApi{
    private val url = "https://opentdb.com/api.php?amount=20&category=15&difficulty=medium&type=multiple"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json { 
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<Questions>()
}