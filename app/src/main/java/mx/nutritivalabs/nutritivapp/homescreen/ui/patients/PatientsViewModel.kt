package mx.nutritivalabs.nutritivapp.homescreen.ui.patients


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.nutritivalabs.nutritivapp.patient.Patient

class PatientsViewModel : ViewModel() {

    val patientsList = ArrayList<Patient>()
    val patientsListLive = MutableLiveData<ArrayList<Patient>>()

    fun updatePatientsList(patient: Patient?){
        if (patient != null) {
            patientsList.add(patient)
            patientsListLive.value = patientsList
        }
    }

}