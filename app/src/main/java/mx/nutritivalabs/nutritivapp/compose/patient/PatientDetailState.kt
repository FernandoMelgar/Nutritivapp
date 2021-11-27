package mx.nutritivalabs.nutritivapp.compose.patient

import mx.nutritivalabs.nutritivapp.patient.Patient

data class PatientDetailState(
    val isLoading: Boolean = false,
    val patient: Patient? = null,
    val error: String? = ""
)
