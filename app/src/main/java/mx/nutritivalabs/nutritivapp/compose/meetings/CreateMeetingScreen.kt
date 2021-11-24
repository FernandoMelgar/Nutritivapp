package mx.nutritivalabs.nutritivapp.compose.meetings

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import mx.nutritivalabs.nutritivapp.asDate
import mx.nutritivalabs.nutritivapp.domain.Meeting
import mx.nutritivalabs.nutritivapp.simpleFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateMeetingScreen(onCreateMeeting: (Meeting) -> Unit) {
    var patientId by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var start by remember { mutableStateOf("") }
    var end by remember { mutableStateOf("") }

    val dateDialogState = rememberMaterialDialogState()
    val startTimeDialogState = rememberMaterialDialogState()
    val endTimeDialogState = rememberMaterialDialogState()


    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Nueva reunión",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
            OutlinedTextField(
                value = patientId,
                onValueChange = { patientId = it },
                label = { Text("Id paciente") },
                modifier = Modifier.width(150.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = selectedDate,
                onValueChange = { selectedDate = it },
                label = { Text("Fecha") },
                trailingIcon = {
                    Icon(Icons.Filled.DateRange, "")
                },
                enabled = false,
                modifier = Modifier
                    .clickable { dateDialogState.show() }
                    .width(160.dp)
            )

            Row() {
                OutlinedTextField(
                    value = start,
                    onValueChange = { start = it },
                    label = { Text("Inicio") },
                    trailingIcon = {
                        Icon(Icons.Filled.DateRange, "")
                    },
                    enabled = false,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .width(120.dp)
                        .clickable { startTimeDialogState.show() })
                OutlinedTextField(
                    value = end,
                    onValueChange = { end = it },
                    label = { Text("Fin") },
                    trailingIcon = {
                        Icon(Icons.Filled.DateRange, "")
                    },
                    enabled = false,
                    modifier = Modifier
                        .clickable { endTimeDialogState.show() }
                        .width(120.dp)
                )
            }
            Button(
                onClick = {
                    onCreateMeeting(
                        Meeting(
                            id = UUID.randomUUID().toString(),
                            patientName = "",
                            patientId = patientId,
                            startTime = start,
                            endTime = end,
                            notes = "",
                            meetingInfo = mapOf(),
                            date = selectedDate.asDate()!!
                        )
                    )
                },
                enabled = (patientId.isNotBlank() && selectedDate.isNotBlank() && start.isNotBlank() && end.isNotBlank()),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create")
            }

        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            selectedDate = date.simpleFormat()
        }
    }


    MaterialDialog(
        dialogState = startTimeDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker { time ->
            start = time.toString()
        }

    }


    MaterialDialog(
        dialogState = endTimeDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker { time ->
            end = time.toString()
        }
    }


}