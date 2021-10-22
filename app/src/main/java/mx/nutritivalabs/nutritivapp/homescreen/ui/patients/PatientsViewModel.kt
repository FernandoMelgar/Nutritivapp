package mx.nutritivalabs.nutritivapp.homescreen.ui.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PatientsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Patients info will be here. :)"
    }
    val text: LiveData<String> = _text
}