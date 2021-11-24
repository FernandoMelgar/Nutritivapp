package mx.nutritivalabs.nutritivapp.compose.patient

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import mx.nutritivalabs.nutritivapp.compose.meetings.MeetingViewModel
import mx.nutritivalabs.nutritivapp.domain.Meeting
import mx.nutritivalabs.nutritivapp.patient.Patient
import mx.nutritivalabs.nutritivapp.patient.examplePatient
import java.util.*

@Composable
fun PatientsScreen(onCreatePatient: (Patient) -> Unit) {
    val patient = examplePatient()

    Button(onClick = { onCreatePatient(patient) }) {
        Text("Create")
    }
}