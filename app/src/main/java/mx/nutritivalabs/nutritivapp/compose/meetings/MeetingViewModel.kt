package mx.nutritivalabs.nutritivapp.compose.meetings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.nutritivalabs.nutritivapp.asDate
import mx.nutritivalabs.nutritivapp.compose.Result
import mx.nutritivalabs.nutritivapp.compose.meetings.state.MeetingDetailState
import mx.nutritivalabs.nutritivapp.compose.meetings.state.MeetingListDetailState
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


    var selectedDate = mutableStateOf("")

    fun findMeetings(date: String, nutritionistId: Long) {
        meetingRepository.getMeetingsByDate(date, nutritionistId).onEach { result ->
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

    fun findByID(id: String) {
        meetingRepository.findById(id).onEach { result ->
            when(result) {
                is Result.Error -> {
                    _state.value = MeetingDetailState(error = result.message  ?: "Error inesperado" )
                }
                is Result.Loading -> {
                    _state.value = MeetingDetailState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = MeetingDetailState(meeting = result.data ?: emptyMeeting())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun findLastTen(patientId: String) {
        meetingRepository.getByPatient(patientId).onEach { result ->
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

    fun onDateSelect(date: String) {
        this.findMeetings(date, 1)
    }

}

fun emptyMeeting(): Meeting {
    return Meeting("","01/01/2021".asDate()!!,"","","","","", mapOf())
}
