package mx.nutritivalabs.nutritivapp.compose.patient

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import mx.nutritivalabs.nutritivapp.patient.examplePatient

@Composable
fun PatientsScreen() {
    val vm: PatientViewModel = hiltViewModel()

    Button(onClick = { vm.addNewPatient(examplePatient()) }) {
        Text("Create")
    }
}