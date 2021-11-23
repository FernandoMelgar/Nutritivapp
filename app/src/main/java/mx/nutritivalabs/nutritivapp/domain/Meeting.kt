package mx.nutritivalabs.nutritivapp.domain

import java.util.*

data class Meeting(
    val id: Long? = null,
    val date: Date,
    val patientId: Long? = null,
    val patientName: String,
    val startTime: Int,
    val endTime: Int,
    val notes: String,
    val meetingInfo: Map<String, String>
)
