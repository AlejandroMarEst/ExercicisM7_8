package cat.itb.m78.exercices.Maps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.db.Markers
import coil3.compose.AsyncImage

@Composable
fun PointerInfo(id : Int){
    val viewModel = viewModel{PointerInfoViewModel(id)}
    PointerInfoScreen(
        viewModel.pointer
    )
}

@Composable
fun PointerInfoScreen(
    pointer: Markers
){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        AsyncImage(
            model = pointer.Photo,
            contentDescription = null
        )
        Text(pointer.Name)
        Text(pointer.Description)
        Text(pointer.Lat.toString() + ", " + pointer.Long.toString())
    }
}