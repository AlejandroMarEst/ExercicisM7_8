package cat.itb.m78.exercices.triviaIndies

data class ConfigTrivia(
    val rounds: Int = 10,
    val difficulty: Int = 3,
    val time: Int = 10
)

data object ConfigTriviaUpdater{
    private var settings = ConfigTrivia()
    fun update(newSettings: ConfigTrivia){
        settings = newSettings
    }
    fun get() = settings
}