package mx.nutritivalabs.nutritivapp.homescreen.ui.home

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
import mx.nutritivalabs.nutritivapp.Screen
import mx.nutritivalabs.nutritivapp.compose.MeetingViewModel
import mx.nutritivalabs.nutritivapp.domain.Meeting
import mx.nutritivalabs.nutritivapp.ui.theme.LightRed
import java.util.*

@Composable
fun ScheduleScreen(navController: NavHostController, meetingViewModel: MeetingViewModel) {
    val scrollState = rememberScrollState()

    val selectedDate = Calendar.getInstance()
    var selectedDay by remember { mutableStateOf(selectedDate.get(Calendar.DAY_OF_MONTH)) }
    selectedDate.set(Calendar.DAY_OF_YEAR, selectedDay)

    val meetings = meetingViewModel.findMeetings(selectedDate.time, 1)


    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column {
            GreetingSection()
            Spacer(modifier = Modifier.height(24.dp))
            CalendarSection { selectedDay = it }
            Text(text = selectedDay.toString())
            Spacer(modifier = Modifier.height(24.dp))
            MeetingSection(navController, meetings)
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

data class MeetingDetail(
    val id: Long,
    val patientName: String,
    val startTime: String,
    val endTime: String
)

fun getTestsMeetings(day: Int, month: Int): List<Meeting> {
    val viewModel = MeetingViewModel()
    val calendar = Calendar.getInstance()
    calendar.set(2021, month, day)
    return viewModel.findMeetings(calendar.time, 1)

}

@Composable
fun MeetingSection(navController: NavHostController, meetings: List<Meeting>) {
    for (meet in meetings) {
        MeetingChip(
            patientName = meet.patientName,
            desc = "${meet.date}: ${meet.startTime} - ${meet.endTime}",
            height = 200,
            color = LightRed,
            onClick = { navController.navigate(Screen.Meeting.withId(1)) }
        )
    }
}
