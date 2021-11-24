package mx.nutritivalabs.nutritivapp.domain

import mx.nutritivalabs.nutritivapp.simpleDateFormat
import java.util.*

data class Meeting(
    val id: String? = null,
    val date: Date,
    val patientId: String? = null,
    val patientName: String,
    val startTime: String,
    val endTime: String,
    val notes: String,
    val meetingInfo: Map<String, String>
) {
    val dateAsString: String
        get() = this.date.simpleDateFormat()
}
