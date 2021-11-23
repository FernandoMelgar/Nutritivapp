package mx.nutritivalabs.nutritivapp.compose

import androidx.lifecycle.ViewModel
import mx.nutritivalabs.nutritivapp.domain.Meeting
import java.util.*

class MeetingViewModel(private val repository: MeetingRepository = MeetingOnMemoryRepository()) : ViewModel() {

    fun findMeetings(date: Date, nutritionistId: Long): List<Meeting> {
        return repository.findMeetings(date, nutritionistId)
    }

    fun findByID(id: Long): Meeting {
        return repository.findMeeting(id)
    }

    fun findLastTen(patientId: Long): List<Meeting> {
        return repository.findLastTenByPatient(patientId)
    }

}