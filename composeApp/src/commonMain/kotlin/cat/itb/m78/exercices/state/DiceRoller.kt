package cat.itb.m78.exercices.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.dice_1
import m78exercices.composeapp.generated.resources.dice_2
import m78exercices.composeapp.generated.resources.dice_3
import m78exercices.composeapp.generated.resources.dice_4
import m78exercices.composeapp.generated.resources.dice_5
import m78exercices.composeapp.generated.resources.dice_6
import m78exercices.composeapp.generated.resources.empty_dice
import m78exercices.composeapp.generated.resources.tapestry
import m78exercices.composeapp.generated.resources.title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random

@Composable
fun Gambling(){
    var dice1 by remember { mutableStateOf(0) }
    var dice2 by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, ) {
        Image(
            painter = painterResource(Res.drawable.tapestry),
            contentDescription = null, modifier = Modifier.size(10000.dp, 10000.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.title),
                modifier = Modifier.size(200.dp).clip(CircleShape),
                contentDescription = null
            )
            Button(
                onClick = {
                    dice1 = rollDice()
                    dice2 = rollDice()
                    if(dice1==dice2&&dice1==6){
                        scope.launch { snackbarHostState.showSnackbar("Jackpot!!!") }
                    }
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Roll the dice!")
            }
            Row {
                Dice(dice1)
                Dice(dice2)
            }
        }
    }
}
fun rollDice() : Int {
    return Random.nextInt(1, 7)
}
@Composable
fun Dice(dice : Int) {
    Image(
        painter = painterResource(drawDice(dice)),
        contentDescription = null
    )
}
fun drawDice(dice : Int) : DrawableResource {
    when (dice) {
        1 -> return Res.drawable.dice_1
        2 -> return Res.drawable.dice_2
        3 -> return Res.drawable.dice_3
        4 -> return Res.drawable.dice_4
        5 -> return Res.drawable.dice_5
        6 -> return Res.drawable.dice_6
    }
    return Res.drawable.empty_dice
}