package cat.itb.m78.exercices.triviaIndies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import m78exercices.composeapp.generated.resources.Res

class GameScreenViewModelTrivia : ViewModel() {
    val settings = ConfigTriviaUpdater.get()
    val questionCounter = mutableStateOf(1)
    var currentQuestion = mutableStateOf<Question?>(null)
    val score = mutableStateOf(0)
    var questionsList = mutableStateOf<List<Question>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            questionsList.value = QuestionApi.list().results
            currentQuestion.value = generateQuestion()
        }
    }

    private fun shuffleQuestions(){
        if (questionsList.value!=null){
            val questionsShuffle = questionsList.value
            questionsList.value = questionsShuffle!!.shuffled()
        }
    }


    private fun generateQuestion(): Question {
        shuffleQuestions()
        val question = questionsList.value
        question!![0].randomizeAnswers()
        return getLessAnswers(question[0])
    }


    private fun getLessAnswers(question: Question): Question {
        if (settings.difficulty == 2) {
            return question.getLessAnswers(3)
        } else if (settings.difficulty == 1) {
            return question.getLessAnswers(2)
        }
        return question
    }

    private fun scoreUp(){
        score.value += 100
    }

    fun answer(userAnswer: Int, toScore: (Int) -> Unit){
        if (currentQuestion.value != null) {
            if (userAnswer == currentQuestion.value!!.correctPosition) {
                scoreUp()
            }
            questionCounter.value++
            questionsList.value!![questionCounter.value - 1].randomizeAnswers()
            currentQuestion.value = getLessAnswers(questionsList.value!![questionCounter.value - 1])
            if (questionCounter.value == settings.rounds + 1) {
                toScore(score.value)
            }
        }
    }
}