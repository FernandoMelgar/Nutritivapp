package mx.nutritivalabs.nutritivapp.compose.patient

import mx.nutritivalabs.nutritivapp.patient.Patient

data class PatientListDetailState(
        val isLoading: Boolean = false,
        val patients: List<Patient> = listOf(),
        val error: String? = ""
)