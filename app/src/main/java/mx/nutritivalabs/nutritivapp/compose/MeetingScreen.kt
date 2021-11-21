package mx.nutritivalabs.nutritivapp.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.nutritivalabs.nutritivapp.patient.examplePatient

@Composable
fun MeetingScreen(onPatientInfo: () -> Unit) {
    val scrollState = rememberScrollState()
    val patient = examplePatient()
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        UpperBar(title = "Cita")

        Box(Modifier.clickable { onPatientInfo() }) {
            PatientInfoChip(
                patient.profilePictureURL,
                patient.fullName,
                patient.memberSince.toString()
            )

        }


    }


}