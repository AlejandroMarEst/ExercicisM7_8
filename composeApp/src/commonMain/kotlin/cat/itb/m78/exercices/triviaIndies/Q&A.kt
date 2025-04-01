package cat.itb.m78.exercices.triviaIndies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Questions(
    @SerialName("results") val results: List<Question>
)

@Serializable
data class Question(
    @SerialName("question") val Question : String,
    @SerialName("correct_answer") val CrrctAnsw : String,
    @SerialName("incorrect_answers") val Answrs : List<String>,
    var answers : List<String> = Answrs + CrrctAnsw,
    var correctPosition : Int = 1
){
    fun randomizeAnswers(){
        val correctAnsw = CrrctAnsw
        answers = answers.shuffled()
        correctPosition = answers.indexOf(correctAnsw) + 1
    }

    fun getLessAnswers(count : Int) : Question{
        val correctAnsw = answers[correctPosition-1]
        if (correctPosition > count){
            val answNum = (1..count).random()
            return Question(Question, CrrctAnsw, Answrs, List(count){
                if(answNum-1==it){
                    correctAnsw
                }else{
                    answers[it]
                }
            }, answNum)
        }
        return Question(Question, CrrctAnsw, Answrs, List(count){answers[it]}, correctPosition)
    }
}
