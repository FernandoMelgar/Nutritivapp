package mx.nutritivalabs.nutritivapp.nutritionist

import java.time.LocalDate

data class Nutritionist(
    val firstName: String,
    val paternalLastName: String?,
    val maternalLastName: String?,
    val birthDate: LocalDate
) {
    val fullName: String
        get() = "$firstName $paternalLastName $maternalLastName"

}