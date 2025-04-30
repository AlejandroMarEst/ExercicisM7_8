package cat.itb.m78.exercices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cat.itb.m78.exercices.CamEx.CamExNav
import cat.itb.m78.exercices.Maps.MapNav
import cat.itb.m78.exercices.Maps.MapsScreen

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { MapNav() }
    }
}

@Preview
@Composable
fun AppPreview() { CamExNav() }
