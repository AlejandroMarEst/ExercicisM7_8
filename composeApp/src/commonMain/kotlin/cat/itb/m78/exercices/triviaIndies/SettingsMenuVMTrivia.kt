package cat.itb.m78.exercices.triviaIndies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingsMenuViewModelTrivia : ViewModel(){
    private var settings = ConfigTriviaUpdater.get()
    val time = mutableStateOf(settings.time)
    val rounds = mutableStateOf(settings.rounds)
    val difficulty = mutableStateOf(settings.difficulty)
    fun setDifficulty(Difficulty : Int){
        difficulty.value = Difficulty
    }
    fun setRounds(Rounds : Int){
        rounds.value = Rounds
    }
    fun setTime(Time : Int){
        time.value = Time
    }
    fun exitSettings(navigateToMenuScreen: ()->Unit){
        settings = ConfigTrivia(rounds.value, difficulty.value, time.value)
        ConfigTriviaUpdater.update(settings)
        navigateToMenuScreen()
    }
}