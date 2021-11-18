package mx.nutritivalabs.nutritivapp.domain

import java.util.*

data class Meeting(
    val id: Long? = null,
    val date: Date,
    val patientId: Long? = null,
    val patientName: String,
    val startTime: Int,
    val endTime: Int
)

private fun usage() {
    Meeting(
        id = 1,
        date = Calendar.getInstance().time,
        patientId = 2,
        patientName = "Fernando Melgar",
        startTime = 1700,
        endTime = 1750
    )
}