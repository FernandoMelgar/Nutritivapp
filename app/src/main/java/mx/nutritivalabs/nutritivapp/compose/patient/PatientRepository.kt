package mx.nutritivalabs.nutritivapp.compose.patient

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.nutritivalabs.nutritivapp.patient.Patient
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
}