package mx.nutritivalabs.nutritivapp.homescreen.ui.patients


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.nutritivalabs.nutritivapp.patient.Patient

class PatientsViewModel : ViewModel() {

    val patientsList = MutableLiveData<ArrayList<Patient>>()

    fun updatePatientsList(){
        var patientsArr : ArrayList<Patient> = arrayListOf()
        val patient = Patient(0, "hola", "como", "estas")
        val patient2 = Patient(1, "hola", "como", "estas")
        patientsArr.add(patient)
        patientsArr.add(patient2)

        patientsList.value = patientsArr

    }

}