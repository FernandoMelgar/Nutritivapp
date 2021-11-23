package mx.nutritivalabs.nutritivapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import mx.nutritivalabs.nutritivapp.domain.Meeting
import mx.nutritivalabs.nutritivapp.patient.Patient
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun MeetingScreen(
    onPatientInfo: () -> Unit,
    navController: NavController,
    meeting: Meeting,
    lastTenMeetings: List<Meeting>,
    patient: Patient
) {
    val scrollState = rememberScrollState()


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
        DisplayInfoSection(title = "Información de la reunión", data = meeting.meetingInfo)
        HistorialDeReuniones(navController, lastTenMeetings, meeting.id!!)
        Spacer(Modifier.height(120.dp))

    }
}

@Composable
fun HistorialDeReuniones(
    navController: NavController, meetingHistory: List<Meeting>, currentId: Long
) {

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Historial de reuniones  ",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )

            for (meeting in meetingHistory) {
                ReunionAnteriorChip(
                    meeting.date,
                    meeting.notes,
                    current = (currentId == meeting.id)
                ) { navController.navigate(NavigationItem.Meeting.withId(meeting.id!!)) }
            }
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), shape = RoundedCornerShape(8.dp)
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


fun Date.simpleDateFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(this)
}