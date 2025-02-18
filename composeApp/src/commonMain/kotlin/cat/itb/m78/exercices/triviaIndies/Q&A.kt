package cat.itb.m78.exercices.triviaIndies

var questions = listOf(
Question("When was Hollow Knight released?", listOf("2016", "2017", "2018", "2019"), 2),
Question("Who is the main character in Celeste?", listOf("Madeline", "Celeste", "Theo", "Granny"), 1),
Question("Which of these is NOT a weapon in Hades?", listOf("The Stygian Blade", "The Twin Fists", "The Abyssal Pike", "The Adamant Rail"), 3),
Question("In Stardew Valley, what is the name of the town where the game takes place?", listOf("Pelican Town", "Star Valley", "Moonlight Bay", "Harvest Town"), 1),
Question("What is the main goal in Slay the Spire?", listOf("Rescue the king", "Find the relic", "Defeat the heart", "Reach the top floor"), 3),
Question("Which indie game features a character named Hornet?", listOf("Dead Cells", "Blasphemous", "Hollow Knight", "Celeste"), 3),
Question("Who developed Undertale?", listOf("Eric Barone", "Toby Fox", "Edmund McMillen", "Lucas Pope"), 2),
Question("What kind of game is The Binding of Isaac?", listOf("Metroidvania", "Survival Horror", "Point-and-click", "Roguelike"), 4),
Question("In Terraria, what is the final boss of the base game (before updates)?", listOf("The Wall of Flesh", "Skeletron", "Moon Lord", "The Destroyer"), 3),
Question("Which indie game lets you play as a sentient piece of bread?", listOf("Loaf Life", "Toast Simulator", "I Am Bread", "Bread Run"), 3),
Question("In Cuphead, what is the name of Cuphead’s brother?", listOf("Chalice", "Steinboy", "Mugman", "Handlehead"), 3),
Question("Which game is often referred to as a 'reverse bullet hell'?", listOf("Dead Cells", "Enter the Gungeon", "Vampire Survivors", "Nuclear Throne"), 3),
Question("In Outer Wilds, what do you use to explore the solar system?", listOf("A time machine", "A wormhole", "A spaceship", "A flying suit"), 3),
Question("What is the main mechanic of Superhot?", listOf("Time moves when you move", "You have only one life", "Everything is randomly generated", "You can rewind time"), 1),
Question("In Gris, what emotion does the color blue represent?", listOf("Anger", "Sadness", "Happiness", "Fear"), 2),
Question("Which indie game takes place inside a mind-control facility?", listOf("Limbo", "Inside", "Little Nightmares", "Amnesia: Rebirth"), 2)
)

data class Question(val question: String, var answer: List<String>, var correct: Int){
    fun randomizeAnswers(){
        val correctAnsw = answer[correct - 1]
        answer = answer.shuffled()
        correct = answer.indexOf(correctAnsw) + 1
    }
    fun getLessAnswers(count : Int) : Question{
        val correctAnsw = answer[correct-1]
        if (correct > count){
            val answNum = (1..count).random()
            return Question(question, List(count){
                if(answNum-1==it){
                    correctAnsw
                }else{
                    answer[it]
                }
            }, answNum)
        }
        return Question(question, List(count){answer[it]}, correct)
    }
}
