package mx.nutritivalabs.nutritivapp.compose;

import androidx.lifecycle.ViewModel
import mx.nutritivalabs.nutritivapp.patient.Patient
import mx.nutritivalabs.nutritivapp.patient.examplePatient

class PatientViewModel: ViewModel() {

    fun findById(id: Long): Patient {
        return examplePatient()
    }
}
