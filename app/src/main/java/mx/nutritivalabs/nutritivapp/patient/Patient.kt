package mx.nutritivalabs.nutritivapp.patient

import java.time.LocalDate

data class Patient(
    var firstName: String,
    val paternalLastName: String?,
    val maternalLastName: String?,
    val birthDate: LocalDate,
    val energyRequirements: EnergyRequirements?,
    val goals: List<String>,
    val firstTime: Boolean
) {
    val fullName: String
        get() = "$firstName $paternalLastName $maternalLastName"

}

data class EnergyRequirements(
    val calories: Int,
    val proteinPercentage: Int,
    val carbohydratePercentage: Int,
    val lipidPercentage: Int
)
