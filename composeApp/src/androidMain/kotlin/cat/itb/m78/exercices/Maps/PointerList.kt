package cat.itb.m78.exercices.Maps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.db.Markers

@Composable
fun PointerList(toInfo : (Int) -> Unit){
    val viewModel = viewModel{ PointerListViewModel() }
    PointerListDisplay(viewModel.pointerList, toInfo)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PointerListDisplay(pointers: List<Markers>?, toInfo: (id : Int) -> Unit) {
    if (pointers == null) {
                Column(
                    Modifier.fillMaxSize(),
                    Arrangement.Center,
                    Alignment.CenterHorizontally
                ) {
            CircularProgressIndicator()
        }
    } else {
            LazyColumn(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(pointers.size) { pointer ->
                    Button(onClick = {toInfo(pointers[pointer].Id.toInt())}) {
                        Text(pointers[pointer].Name)
                    }
            }
        }
    }
}