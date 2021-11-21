package mx.nutritivalabs.nutritivapp.compose

import mx.nutritivalabs.nutritivapp.domain.Meeting
import java.util.*

interface MeetingRepository {
    fun findMeetings(date: Date, nutritionistId: Long): List<Meeting>
    fun findMeeting(meetingId: Long)
}

class MeetingOnMemoryRepository : MeetingRepository {

    override fun findMeetings(date: Date, nutritionistId: Long): List<Meeting> {
        return mutableListOf(
            Meeting(
                id = 1,
                date = date,
                patientId = 1,
                patientName = "Rubén Villalpando",
                startTime = 1700,
                endTime = 1740
            ),
            Meeting(
                id = 2,
                date = date,
                patientId = 2,
                patientName = "Arturo Marquez",
                startTime = 1800,
                endTime = 1840
            ),
            Meeting(
                id = 3,
                date = date,
                patientId = 3,
                patientName = "Víctor Sánchez",
                startTime = 1900,
                endTime = 1940
            )


        )
    }

    override fun findMeeting(meetingId: Long) {
        TODO("Not yet implemented")
    }
}
