package mx.nutritivalabs.nutritivapp.compose

import mx.nutritivalabs.nutritivapp.domain.Meeting
import java.util.*

interface MeetingRepository {
    fun findMeetings(date: Date, nutritionistId: Long): List<Meeting>
    fun findMeeting(meetingId: Long): Meeting
    fun findLastTenByPatient(patientId: Long): List<Meeting>
}

class MeetingOnMemoryRepository : MeetingRepository {

    override fun findMeetings(date: Date, nutritionistId: Long): List<Meeting> {
        return mutableListOf(
            Meeting(
                id = 101,
                date = date,
                patientId = 1,
                patientName = "Rubén Villalpando",
                startTime = 1700,
                endTime = 1740,
                notes = "Nota 1",
                meetingInfo = mapOf("ID" to "1")
            ),
            Meeting(
                id = 201,
                date = date,
                patientId = 2,
                patientName = "Arturo Marquez",
                startTime = 1800,
                endTime = 1840,
                notes = "Nota 1",
                meetingInfo = mapOf("ID" to "2")
            ),
            Meeting(
                id = 301,
                date = date,
                patientId = 3,
                patientName = "Víctor Sánchez",
                startTime = 1900,
                endTime = 1940,
                notes = "Nota 1",
                meetingInfo = mapOf("ID" to "3")
            )


        )
    }

    override fun findMeeting(meetingId: Long): Meeting {
        return Meeting(
            id = meetingId,
            date = Calendar.getInstance().time,
            patientId = (meetingId / 100),
            patientName = "Meee",
            startTime = 1900,
            endTime = 1940,
            notes = "Nota $meetingId",
            meetingInfo = mapOf("ID" to meetingId.toString())
        )
    }

    override fun findLastTenByPatient(patientId: Long): List<Meeting> {
        return mutableListOf(
            Meeting(
                id = (patientId * 100) + 1,
                date = Calendar.getInstance().time,
                patientId = patientId,
                patientName = "Rubén Villalpando",
                startTime = 1700,
                endTime = 1740,
                notes = "Nota 1",
                meetingInfo = mapOf("ID" to "1")
            ),
            Meeting(
                id = (patientId * 100) + 2,
                date = Calendar.getInstance().time,
                patientId = patientId,
                patientName = "Arturo Marquez",
                startTime = 1800,
                endTime = 1840,
                notes = "Nota 1",
                meetingInfo = mapOf("ID" to "2")
            ),
            Meeting(
                id = (patientId * 100) + 3,
                date = Calendar.getInstance().time,
                patientId = patientId,
                patientName = "Jime Trachtman",
                startTime = 1800,
                endTime = 1840,
                notes = "Nota 1",
                meetingInfo = mapOf("ID" to "20")
            )

        )

    }
}


