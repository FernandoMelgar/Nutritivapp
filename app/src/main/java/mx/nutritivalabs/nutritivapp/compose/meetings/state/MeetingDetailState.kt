package mx.nutritivalabs.nutritivapp.compose.meetings.state

import mx.nutritivalabs.nutritivapp.compose.meetings.emptyMeeting
import mx.nutritivalabs.nutritivapp.domain.Meeting

data class MeetingDetailState(
    val isLoading: Boolean = false,
    val meeting: Meeting? = emptyMeeting(),
    val error: String? = ""
)