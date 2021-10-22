package mx.nutritivalabs.nutritivapp.patient

import java.time.LocalDate

data class Patient(
    var id: Int = 0,
    var firstName: String,
    val paternalLastName: String?,
    val maternalLastName: String?,
    val birthDate: LocalDate? = null,
    val energyRequirements: EnergyRequirements? = null,
    val goals: List<String>? = null,
    val firstTime: Boolean = true
) {
    val fullName: String
        get() = "$firstName $paternalLastName $maternalLastName"

}

data class EnergyRequirements(
    val calories: Int = 0,
    val proteinPercentage: Int = 0,
    val carbohydratePercentage: Int = 0,
    val lipidPercentage: Int = 0
)
