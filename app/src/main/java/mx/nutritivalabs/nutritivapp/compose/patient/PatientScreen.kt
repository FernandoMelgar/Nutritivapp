package mx.nutritivalabs.nutritivapp.compose.patient

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.nutritivalabs.nutritivapp.compose.DisplayInfoSection
import mx.nutritivalabs.nutritivapp.compose.GreetingSection
import mx.nutritivalabs.nutritivapp.compose.PatientInfoChip
import mx.nutritivalabs.nutritivapp.patient.Patient

@Composable
fun PatientsScreen(
    patientViewModel: PatientViewModel,
    onPatientInfo: (String) -> Unit,
    onPatientCreate: () -> Unit
) {
    val stateList = patientViewModel.stateList.value
    Column {
        GreetingSection {

        }
        Button(
            onClick = onPatientCreate, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Text("Crear paciente")
        }

        LazyColumn() {
            items(stateList.patients) { p ->
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
        Spacer(modifier = Modifier.height(200.dp))
    }
}