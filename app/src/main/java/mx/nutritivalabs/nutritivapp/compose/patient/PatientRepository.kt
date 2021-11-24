package mx.nutritivalabs.nutritivapp.compose.patient

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import mx.nutritivalabs.nutritivapp.asDate
import mx.nutritivalabs.nutritivapp.compose.Result
import mx.nutritivalabs.nutritivapp.patient.Patient
import mx.nutritivalabs.nutritivapp.patient.exampleEnergyRequirements
import mx.nutritivalabs.nutritivapp.patient.examplePatient
import mx.nutritivalabs.nutritivapp.toMap
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton


class PatientRepository
constructor(
    private val patientRef: CollectionReference = Firebase.firestore.collection("patients")
) {

    fun addNewPatient(patient: Patient) {
        try {
            patientRef.document(patient.id.toString()).set(patient)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun findAll(): Flow<Result<List<Patient>>> = flow {
        try {
            emit(Result.Loading())
            val list = patientRef.get().await().map { patient ->
                val json = JSONObject(patient.get("clinicData").toString())
                val jsonClinic = json.toMap()
                Patient(
                    id = patient.get("id").toString(),
                    firstName = patient.get("firstName").toString(),
                    paternalLastName = patient.get("paternalLastName").toString(),
                    maternalLastName = patient.get("maternalLastName").toString(),
                    memberSince = patient.get("memberSinceAsText").toString().asDate(),
                    birthDate = patient.get("birthDateAsText").toString().asDate(),
                    energyRequirements = exampleEnergyRequirements(),
                    goals = listOf(),
                    firstTime = patient.get("firstTime").toString().toBoolean(),
                    email = patient.get("email").toString(),
                    phoneNumber = patient.get("phoneNumber").toString(),
                    profilePictureURL = patient.get("profilePictureURL").toString(),
                    clinicData = jsonClinic as Map<String, Any>
                )
            }
            emit(Result.Success(data = list))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error desconocido"))
        }
    }

    fun findPatientById(id: String): Flow<Result<Patient>> = flow {
        try {
            emit(Result.Loading())
            val p = patientRef.document(id).get().await().let { patient ->

                val json = JSONObject(patient.get("clinicData").toString())
                val jsonClinic = json.toMap()

                Patient(
                    id = patient.get("id").toString(),
                    firstName = patient.get("firstName").toString(),
                    paternalLastName = patient.get("paternalLastName").toString(),
                    maternalLastName = patient.get("maternalLastName").toString(),
                    memberSince = patient.get("memberSinceAsText").toString().asDate(),
                    birthDate = patient.get("birthDateAsText").toString().asDate(),
                    energyRequirements = exampleEnergyRequirements(),
                    goals = listOf(),
                    firstTime = patient.get("firstTime").toString().toBoolean(),
                    email = patient.get("email").toString(),
                    phoneNumber = patient.get("phoneNumber").toString(),
                    profilePictureURL = patient.get("profilePictureURL").toString(),
                    clinicData = jsonClinic as Map<String, Any>
                )
            }
            emit(Result.Success(data = p))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error desconocido"))
        }
    }
}