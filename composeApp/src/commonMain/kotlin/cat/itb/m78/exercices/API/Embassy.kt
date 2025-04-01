package cat.itb.m78.exercices.API

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.format.Padding
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import m78exercices.composeapp.generated.resources.Res

@Serializable
data class Embassy(
    @SerialName("dia") val date : String,
    @SerialName("estaci") val name: String,
    @SerialName("nivell_absolut") val nivell: Double,
    @SerialName("percentatge_volum_embassat") val volum_percentatge: Double,
    @SerialName("volum_embassat") val volum: Double
)

object EmbassyScreens{
    @Serializable
    data object ScreenEmbassies
    @Serializable
    data object EmbassyInfo
}

object EmbassyApi{
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list(url : String) = client.get(url).body<List<Embassy>?>()
}

@Composable
fun NavResourcesEmbassy(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = EmbassyScreens.ScreenEmbassies) {
        composable<EmbassyScreens.ScreenEmbassies>{
            EmbassyAPIViewModelView({ navController.navigate(EmbassyScreens.EmbassyInfo)} )
        }
        composable<EmbassyScreens.EmbassyInfo> {
            EmbassyInfoAPIViewModelView()
        }
    }
}

class EmbassyAPIViewModel() : ViewModel(){
    private var url = "https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json"
    var embassies = mutableStateOf<List<Embassy>?>(null)
    private val settings: Settings = Settings()
    init {
        viewModelScope.launch(Dispatchers.Default){
            embassies.value = EmbassyApi.list(url)
        }
    }
    fun changeFav(embassy : String){
        settings["fav"] = embassy
    }
}

class EmbassyInfoAPIViewModel() : ViewModel(){
    private var url = "https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json"
    private val settings: Settings = Settings()
    val b : String? = settings["fav"]
    var embassies = mutableStateOf<List<Embassy>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            embassies.value = EmbassyApi.list(url + "?estaci=" + b)
        }
    }
}

@Composable
fun EmbassyInfoAPIViewModelView(){
    val viewModel = viewModel{ EmbassyInfoAPIViewModel() }
    EmbassyInfoAPIDisplay(viewModel.embassies.value)
}

@Composable
fun EmbassyAPIViewModelView(nav : (String) -> Unit){
    val viewModel = viewModel{ EmbassyAPIViewModel() }
    EmbassyDisplay(viewModel.embassies.value, viewModel::changeFav, nav)
}

@Composable
fun EmbassyDisplay(listEmbassy: List<Embassy>?, changeFav : (String) -> Unit, nav: (String) -> Unit) {
    fun changeScreen(i : Int){
        if (listEmbassy!=null) {
            changeFav(listEmbassy[i].name)
            nav(listEmbassy[i].toString())
        }
    }
    LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if (listEmbassy != null) {
            items(listEmbassy.size) { embassy ->
                    TextButton(onClick = { changeScreen(embassy)}, modifier = Modifier.fillMaxSize()){
                        Column(horizontalAlignment = Alignment.CenterHorizontally){
                            Text(listEmbassy[embassy].name)
                            Text(listEmbassy[embassy].date)
                        }
                    }
            }
        }
    }
}



@Composable
fun EmbassyInfoAPIDisplay( embassies: List<Embassy>? ) {
    LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        if (embassies != null) {
            items(embassies) { embassy ->
                Column(){
                    Text(embassy.name)
                    Text(embassy.date)
                    Text("Water level: " + embassy.volum)
                    Text("Water volumen: " + embassy.nivell)
                    Text("Water volumen percentage: " + embassy.volum_percentatge + "%")
                    Spacer(Modifier.height(15.dp))
                }
            }
        }
    }
}