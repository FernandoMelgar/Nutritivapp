package mx.nutritivalabs.nutritivapp.compose.meetings

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import mx.nutritivalabs.nutritivapp.asDate
import mx.nutritivalabs.nutritivapp.compose.CustomDropdownMenu
import mx.nutritivalabs.nutritivapp.compose.UpperBar
import mx.nutritivalabs.nutritivapp.compose.patient.PatientViewModel
import mx.nutritivalabs.nutritivapp.domain.Meeting
import mx.nutritivalabs.nutritivapp.simpleFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateMeetingScreen(
    navController: NavController,
    meetingViewModel: MeetingViewModel,
    patientViewModel: PatientViewModel
) {
    var patientId by remember { mutableStateOf("") }
    var patientName by remember { mutableStateOf("") }

    var selectedDate by remember { mutableStateOf(meetingViewModel.selectedDate.value) }
    var start by remember { mutableStateOf("") }
    var end by remember { mutableStateOf("") }

    val dateDialogState = rememberMaterialDialogState()
    val startTimeDialogState = rememberMaterialDialogState()
    val endTimeDialogState = rememberMaterialDialogState()

    val patientListState = patientViewModel.stateList.value
    val patientsOptions = patientListState.patients.map { it.fullName to (it.id ?: "") }.toMap()

    val context = LocalContext.current

    Column {
        UpperBar(title = "Nueva cita")


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
                    text = "Nueva cita",
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondary
                )
                CustomDropdownMenu(
                    options = patientsOptions,
                    onSelected = { id, name ->
                        patientId = id
                        patientName = name
                    },
                    label = "Paciente",
                    value = patientId
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
                        .width(180.dp)
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
                        meetingViewModel.addNewMeeting(
                            Meeting(
                                id = UUID.randomUUID().toString(),
                                patientName = patientName,
                                patientId = patientId,
                                startTime = start,
                                endTime = end,
                                notes = "",
                                meetingInfo = mapOf(),
                                date = selectedDate.asDate()!!
                            )
                        )
                        patientId = ""
                        patientName = ""
                        selectedDate = ""
                        start = ""
                        end = ""
                        Toast
                            .makeText(
                                context,
                                "Sesion Creada",
                                Toast.LENGTH_SHORT
                            ).show()

                    },
                    enabled = (patientId.isNotBlank() && selectedDate.isNotBlank() && start.isNotBlank() && end.isNotBlank()),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Crear")
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

}

data class CreateMeetingDefaults(
    val selectedDate: String = ""
)
