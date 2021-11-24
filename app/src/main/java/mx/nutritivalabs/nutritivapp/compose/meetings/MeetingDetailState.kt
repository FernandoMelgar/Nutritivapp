package mx.nutritivalabs.nutritivapp.compose.meetings

import mx.nutritivalabs.nutritivapp.domain.Meeting
import mx.nutritivalabs.nutritivapp.patient.Patient

data class MeetingDetailState(
    val isLoading: Boolean = false,
    val meeting: Meeting? = emptyMeeting(),
    val error: String? = ""
)