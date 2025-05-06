package cat.itb.m78.exercices


import androidx.compose.runtime.*
import cat.itb.m78.exercices.examAPI.ExamNav
import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    ExamNav()
}