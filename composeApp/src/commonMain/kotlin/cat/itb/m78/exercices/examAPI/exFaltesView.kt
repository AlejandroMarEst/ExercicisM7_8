package cat.itb.m78.exercices.examAPI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FaltesVIewModelView(ToAlumnes : () -> Unit){
    val viewmodel = viewModel{FaltesViewModel()}
    FaltesView(ToAlumnes, viewmodel.faltesWithName.value)
}

@Composable
fun FaltesView(ToAlumnes: () -> Unit, Faltas : MutableList<FaltaName>?){
    Scaffold(bottomBar = {
        BottomAppBar(actions = {
            Row(
                Modifier.fillMaxSize().background(color = Color.hsv(193.92f, 0.5f, 0.8f)),
                horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
                TextButton(onClick = { ToAlumnes() }, Modifier.size(250.dp)) {
                    Row {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Menu description",
                        )
                        Text("Alumnes")
                    }
                }
                TextButton(onClick = { }, Modifier.size(250.dp)) {
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
        if (Faltas!=null) {
            LazyColumn(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(Faltas) { falta ->
                    Card(Modifier.width(400.dp).height(100.dp).padding(top = 20.dp))
                    {
                        Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally){
                            Row(Modifier.padding(10.dp)){
                                Text( falta.name)
                                Spacer(Modifier.size(15.dp))
                                Text( falta.date )
                            }
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