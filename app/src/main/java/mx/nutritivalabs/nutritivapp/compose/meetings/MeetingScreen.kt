package mx.nutritivalabs.nutritivapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.nutritivalabs.nutritivapp.compose.meetings.MeetingViewModel
import mx.nutritivalabs.nutritivapp.compose.navigation.NavigationItem
import mx.nutritivalabs.nutritivapp.compose.patient.PatientViewModel
import mx.nutritivalabs.nutritivapp.domain.Meeting
import mx.nutritivalabs.nutritivapp.patient.Patient
import mx.nutritivalabs.nutritivapp.patient.examplePatient
import mx.nutritivalabs.nutritivapp.simpleDateFormat
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun MeetingScreen(
    navController: NavController,
    meetingViewModel: MeetingViewModel,
    patientViewModel: PatientViewModel,
    onPatientInfo: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val state = meetingViewModel.state.value
    val patientState = patientViewModel.state.value

    val stateList = meetingViewModel.stateList.value

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        UpperBar(title = "Cita")
        if (patientState.patient != null) {
            Box(Modifier.clickable { onPatientInfo() }) {

                PatientInfoChip(
                    modifier = Modifier.padding(16.dp),
                    imgUrl = patientState.patient.profilePictureURL,
                    fullName = patientState.patient.fullName,
                    patientSince = patientState.patient.memberSince?.simpleDateFormat() ?: ""
                )


            }
            if (state.meeting == null) {
                Text("Error al hacer fetch de los datos")
            } else {
                DisplayInfoSection(
                    title = "Información de la cita",
                    data = state.meeting.meetingInfo
                )
                HistorialDeReuniones(navController, stateList.meetings, state.meeting.id!!)
            }
            Text(state.error ?: "")
            Spacer(Modifier.height(120.dp))
        }
    }
}

@Composable
fun HistorialDeReuniones(
    navController: NavController, meetingHistory: List<Meeting>, currentId: String
) {

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Historial de citas  ",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )

            for (meeting in meetingHistory) {
                ReunionAnteriorChip(
                    meeting.date,
                    meeting.id ?: "",
                    current = (currentId == meeting.id)
                ) { navController.navigate(NavigationItem.Meeting.withId(meeting.id!!)) }
            }

            Button(
                onClick = { navController.navigate(NavigationItem.CreateMeeting.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Crear nueva cita")
            }

        }
    }
}

@Composable
fun ReunionAnteriorChip(date: Date, notes: String, current: Boolean, onClick: () -> Unit) {
    val bgColor = if (current) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.primary.copy(alpha = .4f)

    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomStart
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(
                "Fecha: ${date.simpleDateFormat()}",
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                notes,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 11.sp
            )
        }
    }

}


