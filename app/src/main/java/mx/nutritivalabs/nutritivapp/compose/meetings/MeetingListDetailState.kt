package mx.nutritivalabs.nutritivapp.compose.meetings

import mx.nutritivalabs.nutritivapp.domain.Meeting

data class MeetingListDetailState(
    val isLoading: Boolean = false,
    val meetings: List<Meeting> = emptyList(),
    val error: String? = null
    ) {
}