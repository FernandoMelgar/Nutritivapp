package mx.nutritivalabs.nutritivapp.compose.meetings

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import mx.nutritivalabs.nutritivapp.asDate
import mx.nutritivalabs.nutritivapp.compose.Result
import mx.nutritivalabs.nutritivapp.domain.Meeting
import mx.nutritivalabs.nutritivapp.simpleDateFormat
import mx.nutritivalabs.nutritivapp.toMap
import org.json.JSONObject
import java.util.*


class MeetingRepository(
    private val meetingRef: CollectionReference = Firebase.firestore.collection("meetings")
) {

    fun addNewMeeting(meeting: Meeting) {
        try {
            meetingRef.document(meeting.id.toString()).set(meeting)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMeetingsByDate(date: String, nutritionistId: Long): Flow<Result<List<Meeting>>> = flow {
        try {
            emit(Result.Loading())
            val list = meetingRef.get().await()
                .filter{ meeting ->
                    meeting.get("dateAsString").toString() == date
            }.map { meeting ->
                Meeting(
                    id = meeting.get("id").toString(),
                    date = meeting.get("dateAsString").toString().asDate()!!,
                    patientId = meeting.get("patientId").toString(),
                    patientName = meeting.get("patientName").toString(),
                    startTime = meeting.get("startTime").toString(),
                    endTime = meeting.get("endTime").toString(),
                    notes = meeting.get("date").toString(),
                    meetingInfo = mapOf()
                )
            }
            emit(Result.Success(data = list))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error desconocido"))
        }
    }

    fun findById(id: String) = flow {
        try {
            emit(Result.Loading())
            val meeting = meetingRef.document(id).get().await().let { meeting ->

                val json = JSONObject(meeting.get("meetingInfo").toString())
                val meetingInfo = json.toMap()
                Meeting(
                    id = meeting.get("id").toString(),
                    date = meeting.get("dateAsString").toString().asDate()!!,
                    patientId = "1",
                    patientName = meeting.get("patientName").toString(),
                    startTime = meeting.get("startTime").toString(),
                    endTime = meeting.get("endTime").toString(),
                    notes = meeting.get("date").toString(),
                    meetingInfo = meetingInfo as Map<String, Any>
                )
            }
            emit(Result.Success(data = meeting))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Error desconocido"))
        }
    }


}

