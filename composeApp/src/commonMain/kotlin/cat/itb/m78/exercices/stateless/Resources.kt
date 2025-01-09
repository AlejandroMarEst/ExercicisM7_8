package cat.itb.m78.exercices.stateless

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.example
import m78exercices.composeapp.generated.resources.generatedFace

import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Resources(){
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        val stringValue = stringResource(Res.string.example)
        Text(stringValue, fontSize = 2.em)
        Image(
            painter = painterResource(Res.drawable.generatedFace),
            modifier = Modifier.size(200.dp),
            contentDescription = null

        )
    }
}