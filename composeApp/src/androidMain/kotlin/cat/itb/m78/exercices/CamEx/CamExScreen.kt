package cat.itb.m78.exercices.CamEx

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.koalaplot.core.legend.ColumnLegend


@Composable
fun CamExScreen(ToGallery: (List<String>) -> Unit){
    val viewModel = viewModel{CamExViewModel() }
    val context = LocalContext.current
    val photos = viewModel.photoList
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }
    val surfaceRequest = viewModel.surferRequest.value
    surfaceRequest?.let { request ->
        Box {
            CameraXViewfinder(
                surfaceRequest = request,
                modifier = Modifier.fillMaxSize()
            )
            Row {
                Button({ viewModel.takePhoto(context) }) {
                    Text("Take Photo")
                }
                Button({ ToGallery(photos) }) {
                    Text("To Gallery")
                }
            }
        }
    }
}

