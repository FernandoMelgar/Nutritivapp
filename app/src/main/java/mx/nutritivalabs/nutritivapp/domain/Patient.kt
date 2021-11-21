package mx.nutritivalabs.nutritivapp.patient

import java.io.Serializable
import java.time.LocalDate
import java.util.*

data class Patient(
    var id: Int = 0,
    var firstName: String,
    val paternalLastName: String?,
    val maternalLastName: String?,
    val memberSince: Date? = null,
    val birthDate: Date? = null,
    val energyRequirements: EnergyRequirements? = null,
    val goals: List<String>? = null,
    val firstTime: Boolean = true,
    val email: String? = null,
    val phoneNumber: String? = null,
    val profilePictureURL: String = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"
) : Serializable {
    val fullName: String
        get() = "$firstName $paternalLastName $maternalLastName"

}

fun examplePatient(): Patient {
    return Patient(
        id = 1,
        firstName = "Fernando Manuel",
        paternalLastName = "Melgar",
        maternalLastName = "Fuentes",
        birthDate = Calendar.getInstance().time,
        energyRequirements = exampleEnergyRequirements(),
        goals = listOf("Be fast", "Be Strong"),
        firstTime = false,
        email = "fernandom.melgar@gmail.com",
        phoneNumber = "5548351244",
        profilePictureURL = "https://images4.alphacoders.com/738/thumb-1920-73806.jpg",
        memberSince = Calendar.getInstance().time
    )
}


data class EnergyRequirements(
    val calories: Int = 0,
    val proteinPercentage: Int = 0,
    val carbohydratePercentage: Int = 0,
    val lipidPercentage: Int = 0
)

fun exampleEnergyRequirements(): EnergyRequirements {
    return EnergyRequirements(
        calories = 2200,
        proteinPercentage = 30,
        carbohydratePercentage = 40,
        lipidPercentage = 30
    )
}
