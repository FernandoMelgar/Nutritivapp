package mx.nutritivalabs.nutritivapp.compose.meetings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.nutritivalabs.nutritivapp.domain.Meeting
import java.util.*


class MeetingViewModel
constructor(
    private val meetingRepository: MeetingRepository = MeetingRepository()
) : ViewModel() {

    fun addNewMeeting(meeting: Meeting) {
        meetingRepository.addNewMeeting(meeting)
    }

    private val _state: MutableState<MeetingDetailState> = mutableStateOf(MeetingDetailState())
    val state: State<MeetingDetailState>
        get() = _state

    private val _stateList: MutableState<MeetingListDetailState> = mutableStateOf(
        MeetingListDetailState()
    )
    val stateList: State<MeetingListDetailState>
        get() = _stateList



    fun findMeetings(date: Date, nutritionistId: Long) {
        meetingRepository.getMeetingsByDate().onEach { result ->
            when(result) {
                is Result.Error -> {
                    _stateList.value = MeetingListDetailState(error = result.message  ?: "Error inesperado" )
                }
                is Result.Loading -> {
                    _stateList.value = MeetingListDetailState(isLoading = true)
                }
                is Result.Success -> {
                    _stateList.value = MeetingListDetailState(meetings = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun findByID(id: String): Meeting {
        return Meeting(
            id = id,
            date = Calendar.getInstance().time,
            patientId = id,
            startTime = "17:00",
            endTime = "16:00",
            notes = "TODO",
            patientName = "TODO",
            meetingInfo = mapOf("TODO" to "TODO")
        )
    }

    fun findLastTen(patientId: String): List<Meeting> {
        return listOf()
    }


}