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
        Question.ramdomizeAnswers()
        if(settings.difficulty == 2){
            Question = Question.getLessAnswers(3)
        }else if(settings.difficulty == 1){
            Question = Question.getLessAnswers(2)
        }
        return Question
    }

    private fun scoreUp(){
        score.value += 100
    }

    fun answer(userAnswer: Int, toScore: (Int) -> Unit){
        questionCounter.value++
        currentQuestion.value = questions[questionCounter.value-1]
        if(userAnswer == currentQuestion.value.correct){
            scoreUp()
        }
        if(questionCounter.value==settings.rounds +1){
            toScore(score.value)
        }
    }
}