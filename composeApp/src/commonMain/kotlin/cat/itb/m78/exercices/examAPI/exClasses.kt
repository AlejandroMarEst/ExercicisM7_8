package cat.itb.m78.exercices.examAPI

import kotlinx.serialization.Serializable

@Serializable
data class Alumns(
    val id : Int,
    val name : String,
    val surnames : String,
    val email : String,
    val photo_link : String
)

data class FaltaBBDD(
    val id_alumne : Int,
    val date : String
)

data class FaltaName(
    val name : String,
    val date : String
)