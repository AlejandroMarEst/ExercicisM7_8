package cat.itb.m78.exercices.stateless
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import cat.itb.m78.exercices.theme.AppTheme
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
fun Welcome(){
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text("Welcome!", fontSize = 2.em)
        Text("Start learning now", fontSize = 1.em, modifier = Modifier.padding(bottom = 60.dp))
        Button(onClick = { }){
            Text("Login")
        }
        Button(onClick = { }){
            Text("Register")
        }
    }
}