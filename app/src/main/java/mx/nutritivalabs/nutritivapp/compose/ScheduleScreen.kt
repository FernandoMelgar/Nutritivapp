package mx.nutritivalabs.nutritivapp.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.asDate
import mx.nutritivalabs.nutritivapp.compose.meetings.MeetingViewModel
import mx.nutritivalabs.nutritivapp.compose.navigation.NavigationItem
import mx.nutritivalabs.nutritivapp.compose.patient.PatientViewModel
import mx.nutritivalabs.nutritivapp.domain.Meeting
import mx.nutritivalabs.nutritivapp.simpleDateFormat
import mx.nutritivalabs.nutritivapp.ui.theme.LightRed
import java.util.*

@Composable
fun ScheduleScreen(
    navController: NavHostController,
    meetingViewModel: MeetingViewModel,
    patientViewModel: PatientViewModel
) {
    val scrollState = rememberScrollState()
    val calendar = Calendar.getInstance()
    var selectedDay by remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }
    val state = meetingViewModel.stateList.value

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column {
            GreetingSection()
            Spacer(modifier = Modifier.height(24.dp))
            CalendarSection {
                meetingViewModel.onDateSelect("$it/11/2021")
                selectedDay = it
            }
            Spacer(modifier = Modifier.height(24.dp))
            MeetingSection(
                meetings = state.meetings,
                onMeetingCreate = { navController.navigate(NavigationItem.CreateMeeting.route) },
                onChipSelect = { meetingId, patientId ->
                    patientViewModel.findById(patientId)
                    navController.navigate(
                        NavigationItem.Meeting.withId(
                            meetingId
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}


@Composable
fun CalendarSection(onDaySelection: (Int) -> Unit) {
    val days = mutableListOf<Int>()
    val c = Calendar.getInstance()
    for (i in c.get(Calendar.DAY_OF_MONTH)..c.getActualMaximum(Calendar.DAY_OF_MONTH))
        days.add(i)
    val month =
        c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) ?: "Error"

    Surface(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = month, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(18.dp))
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                items(days) { day ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.surface)
                            .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
                            .clickable { onDaySelection(day) }
                    ) {
                        Text(day.toString(), color = MaterialTheme.colors.onSurface)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }


}

@Composable
fun GreetingSection() {
    val context = LocalContext.current

    val userName = "Lilly Collins"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.userlilly),
                contentDescription = "User picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = userName,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Glad to have you back!",
                    style = MaterialTheme.typography.body2
                )
            }
        }

        Box(
            Modifier
                .clip(CircleShape)
                .clickable { }) {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Settings",
                tint = Color.Gray,
                modifier = Modifier
                    .size(32.dp)
            )
        }

    }
}

@Composable
fun MeetingChip(
    onClick: () -> Unit,
    patientName: String,
    desc: String,
    height: Int,
    color: Color
) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(height.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomStart
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                patientName,
                color = Color.White,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Text(
                desc, color = Color.White
            )

        }
    }
}


@Composable
fun MeetingSection(
    meetings: List<Meeting>,
    onMeetingCreate: () -> Unit,
    onChipSelect: (String, String) -> Unit
) {
    if (meetings.isEmpty())
        Text("No hay citas.")
    for (meet in meetings) {
        MeetingChip(
            patientName = meet.patientName,
            desc = "${meet.date.simpleDateFormat()}: ${meet.startTime} - ${meet.endTime}",
            height = 200,
            color = LightRed,
            onClick = { onChipSelect(meet.id!!, meet.patientId!!) }
        )
    }

    Button(
        onClick = onMeetingCreate, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Text("Nueva cita")
    }

}

@Composable
fun SettingsScreen() {
    Text("Settings screen")
}