package mx.nutritivalabs.nutritivapp.compose.meetings

import mx.nutritivalabs.nutritivapp.patient.Patient

data class MeetingDetailState(
    val isLoading: Boolean = false,
    val patient: Patient? = null,
    val error: String? = ""
)