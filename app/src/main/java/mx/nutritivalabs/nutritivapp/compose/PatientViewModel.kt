package mx.nutritivalabs.nutritivapp.compose;

import androidx.lifecycle.ViewModel
import mx.nutritivalabs.nutritivapp.patient.Patient
import mx.nutritivalabs.nutritivapp.patient.exampleEnergyRequirements
import mx.nutritivalabs.nutritivapp.patient.examplePatient
import java.util.*

class PatientViewModel: ViewModel() {

    fun findById(id: Long): Patient {
        if (id == 1L)
            return rubenPatient()
        if (id == 2L)
            return arthurPatient()
        if (id == 3L)
            return vicPatient()
        else
            return examplePatient().copy(firstName = "$id")
    }

}



private fun vicPatient(): Patient {
    return Patient(
        id = 1,
        firstName = "Víctor",
        paternalLastName = "Sánchez",
        maternalLastName = " ",
        birthDate = Calendar.getInstance().time,
        energyRequirements = exampleEnergyRequirements().copy(calories = 2800),
        goals = listOf("Legendary", "Nutrition"),
        firstTime = false,
        email = "victor.sanchez@gmail.com",
        phoneNumber = "553648273",
        profilePictureURL = "https://static.wikia.nocookie.net/minion/images/2/27/Vector.png/revision/latest/top-crop/width/360/height/450?cb=20150724225830&path-prefix=es",
        memberSince = Calendar.getInstance().time
    )
}

private fun arthurPatient() = Patient(
    id = 1,
    firstName = "Arturo",
    paternalLastName = "Marquez",
    maternalLastName = " ",
    birthDate = Calendar.getInstance().time,
    energyRequirements = exampleEnergyRequirements().copy(calories = 2800),
    goals = listOf("Be", "Bold"),
    firstTime = false,
    email = "arturo.marquez@gmail.com",
    phoneNumber = "553648273",
    profilePictureURL = "https://i2.wp.com/revistadiners.com.co/wp-content/uploads/2017/03/loganwolverine_800x669.jpg?fit=800%2C669&ssl=1",
    memberSince = Calendar.getInstance().time
)

private fun rubenPatient(): Patient {
    return Patient(
        id = 1,
        firstName = "Rubén",
        paternalLastName = "Villalapando",
        maternalLastName = "Bremmont",
        birthDate = Calendar.getInstance().time,
        energyRequirements = exampleEnergyRequirements().copy(calories = 3000),
        goals = listOf("Be alive", "My Friend"),
        firstTime = false,
        email = "ruben.villalpando@gmail.com",
        phoneNumber = "553648273",
        profilePictureURL = "https://static.wikia.nocookie.net/middle-earth-film-saga/images/7/77/Legolas.png/revision/latest/top-crop/width/360/height/450?cb=20160207050831",
        memberSince = Calendar.getInstance().time
    )
}
