package mx.nutritivalabs.nutritivapp.compose.patient

import com.google.firebase.firestore.CollectionReference
import mx.nutritivalabs.nutritivapp.patient.Patient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PatientRepository
@Inject
constructor(
    private val patientRef: CollectionReference
) {

    fun addNewPatient(patient: Patient) {
        try {
            patientRef.document(patient.id.toString()).set(patient)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}