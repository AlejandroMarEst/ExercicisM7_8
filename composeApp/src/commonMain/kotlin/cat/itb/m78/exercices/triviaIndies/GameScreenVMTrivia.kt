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
        Question.randomizeAnswers()
        return getLessAnswers(Question)
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
        if(userAnswer == currentQuestion.value.correct){
            scoreUp()
        }
        questionCounter.value++
        questions[questionCounter.value-1].randomizeAnswers()
        currentQuestion.value = getLessAnswers(questions[questionCounter.value-1])
        if(questionCounter.value==settings.rounds +1){
            toScore(score.value)
        }
    }
}