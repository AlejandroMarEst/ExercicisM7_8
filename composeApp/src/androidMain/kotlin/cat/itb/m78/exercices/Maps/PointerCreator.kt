package cat.itb.m78.exercices.Maps

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun CreateMarker(lat: Double, lon: Double, savedStateHandle: SavedStateHandle, navigateToMap:()->Unit, navigateToCamera:()->Unit){
    val viewModel = viewModel{CreateMarkerViewModel(lat, lon, savedStateHandle)}
    val photoUri = viewModel.photoUri.collectAsState().value
    viewModel.updateLastPhoto()
    CreateMarkerScreen(
        viewModel.title.value,
        viewModel.description.value,
        viewModel::titleChange,
        viewModel::descriptionChange,
        viewModel::addMarker,
        navigateToMap,
        navigateToCamera,
        viewModel::switchUsingLastPhoto,
        viewModel.usingLastPhoto.value,
        photoUri,
        viewModel.lastPhoto
    )
}

@Composable
fun CreateMarkerScreen(
    title: String,
    description: String,
    titleChange: (String)->Unit,
    descriptionChange: (String)->Unit,
    addMarker: (()->Unit, Boolean)->Unit,
    navigateToMap: () -> Unit,
    navigateToCamera:()->Unit,
    switchUsingLastPhoto : () -> Unit,
    usingLastPhoto : Boolean,
    photoUri: String?,
    lastPhoto: String?
){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        TextField(
            title,
            label = {Text("Title")},
            onValueChange = titleChange
        )
        TextField(
            description,
            label = {Text("Description")},
            onValueChange = descriptionChange
        )
        TextButton(onClick = { navigateToCamera() }){
            if (usingLastPhoto) {
                AsyncImage(
                    model = lastPhoto,
                    contentDescription = null
                )
            } else if(photoUri!=null) {
                AsyncImage(
                    model = photoUri,
                    contentDescription = null
                )
            } else {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = null
                )
            }
        }
        if (lastPhoto != null) {
            Button(onClick = { switchUsingLastPhoto() }) {
                if (!usingLastPhoto) {
                    Text("Use last photo taken")
                } else {
                    Text("Stop using last photo taken")
                }
            }
        }
        Button(onClick = { (addMarker(navigateToMap, usingLastPhoto)) }) {
            Text("Add Marker")
        }
    }
}