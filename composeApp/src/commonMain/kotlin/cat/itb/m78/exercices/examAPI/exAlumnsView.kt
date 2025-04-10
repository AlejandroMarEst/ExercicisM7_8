package cat.itb.m78.exercices.examAPI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import io.ktor.util.date.GMTDate
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


@Composable
fun AlumnsViewmodelView(ToFaltes : () -> Unit){
    val viewmodel = viewModel{ AlumnsViewModel() }
    AlumnsView(viewmodel.alumns.value, viewmodel.faltaNum.value, ToFaltes, viewmodel::addFalta)
}

@Composable
fun AlumnsView(alumns: List<Alumns>?, faltes : MutableList<Int>?, ToFaltes: () -> Unit, AddFalta : (Int) -> Unit) {
    Scaffold(bottomBar = {
        BottomAppBar(actions = {
            Row(Modifier.fillMaxSize().background(color = Color.hsv(193.92f, 0.5f, 0.8f)),
                horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
                TextButton(onClick = { print("Menu") }, Modifier.size(250.dp)) {
                    Row {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Menu description",
                        )
                        Text("Alumnes")
                    }
                }
                TextButton(onClick = { ToFaltes() }, Modifier.size(250.dp)) {
                    Row {
                        Icon(
                            Icons.Filled.Warning,
                            contentDescription = "Favorite description",
                        )
                        Text("Faltes")
                    }
                }
            }
        })
    }){
        if (alumns!=null && faltes!=null) {
            LazyColumn(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(alumns) { alumn ->
                    Card(onClick = { AddFalta(alumn.id) },
                        Modifier.width(300.dp).height(200.dp).padding(top = 20.dp))
                    {
                        Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally){
                            Row(Modifier.padding(10.dp)){
                                Text(alumn.name + " " + alumn.surnames)
                            }
                            Text(alumn.email)
                            Text("Faltes: " + faltes[alumn.id-1])
                            AsyncImage(
                                model = alumn.photo_link,
                                contentDescription = "Translated description of what the image contains",
                                Modifier.size(100.dp).clip(RoundedCornerShape(80.dp)).padding(10.dp)
                            )
                        }
                    }
                }

            }
        } else{
            Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }
        }
    }
}