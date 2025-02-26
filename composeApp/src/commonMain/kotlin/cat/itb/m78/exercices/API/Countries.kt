package cat.itb.m78.exercices.API

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.APIViewModel
import cat.itb.m78.exercices.Jokes
import cat.itb.m78.exercices.JokesDisplay
import cat.itb.m78.exercices.MyApi
import coil3.compose.AsyncImage
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
data class Country(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("capital") val capital: String,
    @SerialName("media") val media: Media
)

@Serializable
data class Media(
    @SerialName("flag") val flag: String
)

object CountryApi{
    private val url = "https://api.sampleapis.com/countries/countries"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<List<Country>?>()
}

class CountryAPIViewModel() : ViewModel(){
    var countries = mutableStateOf<List<Country>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            countries.value = CountryApi.list()
        }
    }
}

@Composable
fun CountryAPIViewModelView(){
    val viewModel = viewModel{ CountryAPIViewModel() }
    CountryDisplay(viewModel.countries.value)
}

@Composable
fun CountryDisplay(listCountry: List<Country>?){
    LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if (listCountry != null) {
            items(listCountry.size) { country ->
                Row {
                    Text(listCountry[country].name + ": ")
                    Text(listCountry[country].capital)
                }
                AsyncImage(
                    model = listCountry[country].media.flag,
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )
            }
        }
    }
}