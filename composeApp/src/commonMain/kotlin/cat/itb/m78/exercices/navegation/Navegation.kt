package cat.itb.m78.exercices.navegation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

sealed interface Screen{
    data object Menu : Screen
    data object Screen1 : Screen
    data object Screen2 : Screen
    data class Screen3(val message : Boolean) : Screen
}

class NavViewModel : ViewModel(){
    val currentScreen = mutableStateOf<Screen>(Screen.Menu)
    fun navigateTo(option : Screen){
        currentScreen.value = option;
    }
}

@Composable
fun NavegationResources(){
    val viewModel = viewModel { NavViewModel() }
    val currentScreen = viewModel.currentScreen.value
    when(currentScreen) {
        Screen.Menu -> NavegationMenu ({ viewModel.navigateTo(Screen.Screen1) }, { viewModel.navigateTo(Screen.Screen2) }, { viewModel.navigateTo(Screen.Screen3(it)) } )
        Screen.Screen1 -> NavegationScreen1 ({ viewModel.navigateTo(Screen.Menu) })
        Screen.Screen2 -> NavegationScreen2 ({ viewModel.navigateTo(Screen.Menu) })
        is Screen.Screen3 -> NavegationScreen3 ( currentScreen.message , {viewModel.navigateTo(Screen.Menu)})
    }
}

@Composable
fun NavegationMenu(navigateTo1: () -> Unit, navigateTo2: () -> Unit, navigateTo3: (value : Boolean) -> Unit){
    Column {
        Button(
            onClick = { navigateTo1() }, modifier = Modifier.padding(5.dp)
        ) {
            Text("Screen 1")
        }
        Button(
            onClick = { navigateTo2() }, modifier = Modifier.padding(5.dp)
        ) {
            Text("Screen 1")
        }
        Button(
            onClick = { navigateTo3(true) }, modifier = Modifier.padding(5.dp)
        ) {
            Text("Screen 3 - Hi")
        }
        Button(
            onClick = { navigateTo3(false) }, modifier = Modifier.padding(5.dp)
        ) {
            Text("Screen 3 - Bye")
        }
    }
}
@Composable
fun NavegationScreen1( navigateTo: () -> Unit){
    Column(modifier = Modifier.background(Color.Green).fillMaxSize(), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom){
        Text("Screen 1")
        Button(
            onClick = { navigateTo() }, modifier = Modifier.padding(5.dp)
        ) {
            Text("Menu")
        }
    }
}
@Composable
fun NavegationScreen2( navigateTo: () -> Unit){
    Column(modifier = Modifier.background(Color.Red).fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {
        Text("Screen 2")
        Button(
            onClick = { navigateTo() }, modifier = Modifier.padding(5.dp)
        ) {
            Text("Menu")
        }
    }
}
@Composable
fun NavegationScreen3( Message : Boolean, navigateTo: () -> Unit){
    Column(modifier = Modifier.background(Color.Blue).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text("Screen 3")
        if (Message) {
            Text("Hi!")
        } else {
            Text("Bye")
        }
        Button(
            onClick = { navigateTo() }, modifier = Modifier.padding(5.dp)
        ) {
            Text("Menu")
        }
    }
}