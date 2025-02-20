package cat.itb.m78.exercices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.calculator.CalculatorViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.random.Random

@Serializable
data class Id(
    @SerialName("Num") val id: Jokes
)

@Serializable
data class Jokes(
    @SerialName("id") val id: Int,
    @SerialName("type") val type: String,
    @SerialName("setup") val setup: String,
    @SerialName("punchline") val punchline: String
)

object MyApi{
    private val url = "https://api.sampleapis.com/jokes/goodJokes"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<List<Jokes>?>()
}

class APIViewModel() : ViewModel(){
    val currentJoke = mutableStateOf(Random.nextInt(1,388))
    var joke = mutableStateOf<List<Jokes>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            joke.value = MyApi.list()
        }
    }
}

@Composable
fun APIViewModelView(){
    val viewModel = viewModel{ APIViewModel() }
    JokesDisplay(viewModel.currentJoke.value ,viewModel.joke.value)
}

@Composable
fun JokesDisplay(currentJoke : Int, list: List<Jokes>?){
    if(list!=null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(list[currentJoke].setup)
            Text(list[currentJoke].punchline)
        }
    }
}