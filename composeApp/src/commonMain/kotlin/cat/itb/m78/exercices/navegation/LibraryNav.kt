package cat.itb.m78.exercices.navegation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.itb.m78.exercices.state.Gambling
import cat.itb.m78.exercices.state.GoodMornin
import cat.itb.m78.exercices.state.GuessNum
import cat.itb.m78.exercices.state.Hello
import cat.itb.m78.exercices.stateless.Contact
import cat.itb.m78.exercices.stateless.HelloWorld
import cat.itb.m78.exercices.stateless.MessagesList
import cat.itb.m78.exercices.stateless.Resources
import cat.itb.m78.exercices.stateless.Welcome
import cat.itb.m78.exercices.viewModel.CounterResources
import cat.itb.m78.exercices.viewModel.ShoppingResources
import kotlinx.serialization.Serializable

object Destination {
    @Serializable
    data object MainMenu
    @Serializable
    data object StatelessMenu
    @Serializable
    data object StateMenu
    @Serializable
    data object ViewModelMenu
    @Serializable
    data object NavigationMenu
    @Serializable
    data object HelloWorld
    @Serializable
    data object Welcome
    @Serializable
    data object Resource
    @Serializable
    data object Contact
    @Serializable
    data object MessageList
    @Serializable
    data object GoodMorningAndNight
    @Serializable
    data object SayHelloScreen
    @Serializable
    data object SecretNumber
    @Serializable
    data object DiceRoller
    @Serializable
    data object Counter
    @Serializable
    data object ShoppingList
    @Serializable
    data object ManualNav
}

@Composable
fun MainMenu() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.MainMenu) {
        composable<Destination.MainMenu> { MainMenu(
                { navController.navigate(Destination.StatelessMenu)},
                { navController.navigate(Destination.StateMenu)},
                { navController.navigate(Destination.ViewModelMenu)},
                { navController.navigate(Destination.NavigationMenu)}
        ) }
        composable<Destination.StatelessMenu> { StatelessMenu(
            { navController.navigate(Destination.HelloWorld)},
            { navController.navigate(Destination.Welcome)},
            { navController.navigate(Destination.Resource)},
            { navController.navigate(Destination.Contact)},
            { navController.navigate(Destination.MessageList)}
        ) }
        composable<Destination.StateMenu> { StateMenu(
            { navController.navigate(Destination.GoodMorningAndNight)},
            { navController.navigate(Destination.SayHelloScreen)},
            { navController.navigate(Destination.SecretNumber)},
            { navController.navigate(Destination.DiceRoller)}
        ) }
        composable<Destination.ViewModelMenu> { ViewModelMenu(
            { navController.navigate(Destination.Counter)},
            { navController.navigate(Destination.ShoppingList)}
        ) }
        composable<Destination.NavigationMenu> { NavigationMenu(
            { navController.navigate(Destination.ManualNav)}
        ) }
        composable<Destination.HelloWorld> { HelloWorld() }
        composable<Destination.Welcome> { Welcome() }
        composable<Destination.Resource> { Resources() }
        composable<Destination.Contact> { Contact() }
        composable<Destination.MessageList> { MessagesList() }
        composable<Destination.GoodMorningAndNight> { GoodMornin() }
        composable<Destination.SayHelloScreen> { Hello() }
        composable<Destination.SecretNumber> { GuessNum() }
        composable<Destination.DiceRoller> { Gambling() }
        composable<Destination.Counter> { CounterResources() }
        composable<Destination.ShoppingList> { ShoppingResources() }
        composable<Destination.ManualNav> { NavegationResources() }
    }
}

@Composable
fun MainMenu(
    Stateless: () -> Unit,
    State: () -> Unit,
    viewModel: () -> Unit,
    Navigation: () -> Unit
) {
    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Button(
            onClick = Stateless, modifier = Modifier.padding(5.dp)
        ) {
            Text("Stateless")
        }
        Button(
            onClick = State, modifier = Modifier.padding(5.dp)
        ) {
            Text("State")
        }
        Button(
            onClick = viewModel, modifier = Modifier.padding(5.dp)
        ) {
            Text("View Model")
        }
        Button(
            onClick = Navigation, modifier = Modifier.padding(5.dp)
        ) {
            Text("Navigation")
        }
    }
}

@Composable
fun StatelessMenu(
    Hello: () -> Unit,
    Welcome: () -> Unit,
    Resources: () -> Unit,
    Contact: () -> Unit,
    Messages: () -> Unit
) {
    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Button(
            onClick = Hello, modifier = Modifier.padding(5.dp)
        ) {
            Text("Hello world")
        }
        Button(
            onClick = Welcome, modifier = Modifier.padding(5.dp)
        ) {
            Text("Welcome")
        }
        Button(
            onClick = Resources, modifier = Modifier.padding(5.dp)
        ) {
            Text("Resources")
        }
        Button(
            onClick = Contact, modifier = Modifier.padding(5.dp)
        ) {
            Text("Contact")
        }
        Button(
            onClick = Messages, modifier = Modifier.padding(5.dp)
        ) {
            Text("Messages")
        }
    }
}

@Composable
fun StateMenu(
    GoodMornin: () -> Unit,
    Secret: () -> Unit,
    Hello: () -> Unit,
    Dice: () -> Unit
) {
    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Button(
            onClick = GoodMornin, modifier = Modifier.padding(5.dp)
        ) {
            Text("Good Morning")
        }
        Button(
            onClick = Secret, modifier = Modifier.padding(5.dp)
        ) {
            Text("Secret Number")
        }
        Button(
            onClick = Hello, modifier = Modifier.padding(5.dp)
        ) {
            Text("Say Hello")
        }
        Button(
            onClick = Dice, modifier = Modifier.padding(5.dp)
        ) {
            Text("Let's go gambling")
        }
    }
}

@Composable
fun ViewModelMenu(
    Counter: () -> Unit,
    Shopping: () -> Unit
) {
    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Button(
            onClick = Counter, modifier = Modifier.padding(5.dp)
        ) {
            Text("Counter")
        }
        Button(
            onClick = Shopping, modifier = Modifier.padding(5.dp)
        ) {
            Text("Shopping List")
        }
    }
}

@Composable
fun NavigationMenu(NavManual: () -> Unit) {
    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Button(
            onClick = NavManual, modifier = Modifier.padding(5.dp)
        ) {
            Text("Manual Nav")
        }
    }
}