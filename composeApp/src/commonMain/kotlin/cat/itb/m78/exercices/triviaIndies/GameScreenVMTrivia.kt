package cat.itb.m78.exercices.triviaIndies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameScreenViewModelTrivia : ViewModel() {
    val settings = ConfigTriviaUpdater.get()
    val questionCounter = mutableStateOf(1)
    val currentQuestion = mutableStateOf(generateQuestion())
    val score = mutableStateOf(0)

    private fun generateQuestion(): Question {
        questions = questions.shuffled()
        var Question = questions[0]
        Question.shuffleAnswers()
        if(settings.difficulty == 2){
            Question = Question.getAnswers(3)
        }else if(settings.difficulty == 1){
            Question = Question.getAnswers(2)
        }
        return Question
    }

    private fun generateAnswers(){
        currentQuestion.value.shuffleAnswers()
        if(settings.difficulty == 2){
            currentQuestion.value = currentQuestion.value.getAnswers(3)
        }else if(settings.difficulty == 1){
            currentQuestion.value = currentQuestion.value.getAnswers(2)
        }
    }

    private fun scoreUp(){
        score.value += 100
        questionCounter.value++
        currentQuestion.value = questions[questionCounter.value-1]
    }

    fun answer(userAnswer: Int, toScore: (Int) -> Unit){
        if(userAnswer == currentQuestion.value.correct){
            scoreUp()
        }
        if(questionCounter.value==settings.rounds){
            toScore(score.value)
        }
    }
}