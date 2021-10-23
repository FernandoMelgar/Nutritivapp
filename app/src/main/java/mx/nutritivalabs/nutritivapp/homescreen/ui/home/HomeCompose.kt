package mx.nutritivalabs.nutritivapp.homescreen.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme
import java.util.*

@Composable
fun DateCircle(text: String, isSelected: Boolean = false) {
    val backgroundColor =
        if (isSelected)
            MaterialTheme.colors.primary
        else
            MaterialTheme.colors.background

    val textColor = if (isSelected)
        MaterialTheme.colors.onPrimary
    else
        MaterialTheme.colors.onBackground

    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary)
        )
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(backgroundColor)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = textColor)
        }
    }
}



@Composable
fun DateCircleList(modifier: Modifier = Modifier, dateLabelList: List<String>) {
    LazyRow(
        modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dateLabelList) { date ->
            DateCircle(date)
        }
    }
}

@Composable
fun DateCircleComponent() {
    val eventsByDay = hashMapOf<String, String>()
    val dateList = mutableListOf<String>()
    val today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    val selectedDay = remember { mutableStateOf(today.toString()) }

    for (i in 0..10) {
        val nextDate = (today + i).toString()
        dateList.add(nextDate)
        eventsByDay[nextDate] = "Eventos nuevosss para $nextDate"
    }

}

@Composable
fun PatientMeetingCard(meeting: Meeting) {
    val patientName = "Ricky Rikin"
    val hora = "17:00 hrs - 17:50 hrs"

    Surface(
        Modifier
            .clickable { }
            .padding(8.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(meeting.patient, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Text(meeting.date.toString(), color = MaterialTheme.colors.onPrimary)
            Text(meeting.place)
        }
    }
}

@Preview
@Composable
fun PreviewDateCircle() {
    NutritivappTheme() {
        Row {
            DateCircle("22")
            DateCircle(text = "23", true)
        }
    }
}

@Preview
@Composable
fun PreviewDateCircleList() {
    NutritivappTheme {
        val dates: List<String> = arrayOf("17", "18", "19", "20", "21", "22", "23").toList()
        Scaffold() {
            DateCircleList(modifier = Modifier.fillMaxWidth(), dateLabelList = dates)
        }
    }
}

@Preview
@Composable
fun PreviewPatientMeetingCard() {
    NutritivappTheme {
        val today = Calendar.getInstance().time
        PatientMeetingCard(Meeting(today, "Patient1", "Zoom1"))
    }
}

@Composable
fun MeetingList(list: List<Meeting>){
    LazyColumn() {
        items(list) { meeting ->
            PatientMeetingCard(meeting = meeting)
        }
    }
}

@Preview
@Composable
fun PreviewMeetingList() {

    val today = Calendar.getInstance().time
    val todayMeetings = DayMeetings(
        today, listOf(
            Meeting(today, "Patient1", "Zoom1"),
            Meeting(today, "Patient2", "Zoom2"),
            Meeting(today, "Patient3", "Zoom3"),
            Meeting(today, "Patient4", "Zoom4")
        )
    )

    NutritivappTheme() {
        MeetingList(todayMeetings.list)
    }
}

data class DayMeetings(val date: Date, val list: List<Meeting>)
data class Meeting(val date: Date, val patient: String, val place: String)

fun mockDayMeetings(): List<DayMeetings> {
    val today = Calendar.getInstance().time
    val todayMeetings = DayMeetings(
        today, listOf(
            Meeting(today, "Patient1", "Zoom1"),
            Meeting(today, "Patient2", "Zoom2"),
            Meeting(today, "Patient3", "Zoom3")
        )
    )
    val tomorrow = Calendar.getInstance()
    tomorrow.add(Calendar.DATE, 1)
    val tomorrowMeetings = DayMeetings(
        tomorrow.time, listOf(
            Meeting(tomorrow.time, "Patient4", "Zoom4"),
            Meeting(tomorrow.time, "Patient5", "Zoom5"),
        )
    )

    return listOf(todayMeetings, tomorrowMeetings)
}
