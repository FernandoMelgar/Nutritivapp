package mx.nutritivalabs.nutritivapp.compose.patient

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.nutritivalabs.nutritivapp.compose.GreetingSection
import mx.nutritivalabs.nutritivapp.compose.PatientInfoChip
import mx.nutritivalabs.nutritivapp.patient.Patient

@Composable
fun PatientsScreen(
    patientViewModel: PatientViewModel,
    onPatientInfo: (String) -> Unit
) {
    val stateList = patientViewModel.stateList.value
    Column {
        GreetingSection {

        }

        for (p in stateList.patients) {
            PatientInfoChip(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .clickable { onPatientInfo(p.id!!) },
                imgUrl = p.profilePictureURL,
                fullName = p.fullName,
                patientSince = p.memberSinceAsText ?: ""
            )
        }
    }

}