package mx.nutritivalabs.nutritivapp.compose.patient

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import mx.nutritivalabs.nutritivapp.asDate
import mx.nutritivalabs.nutritivapp.compose.UpperBar
import mx.nutritivalabs.nutritivapp.patient.EnergyRequirements
import mx.nutritivalabs.nutritivapp.patient.Patient
import mx.nutritivalabs.nutritivapp.patient.exampleEnergyRequirements
import mx.nutritivalabs.nutritivapp.simpleFormat
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreatePatientScreen(patientViewModel: PatientViewModel) {
    var firstName by remember { mutableStateOf("") }
    var paternalLastName by remember { mutableStateOf("") }
    var maternalLastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var carbs by remember { mutableStateOf("") }
    var protein by remember { mutableStateOf("") }
    var lipids by remember { mutableStateOf("") }


    var selectedDate by remember { mutableStateOf("") }
    val dateDialogState = rememberMaterialDialogState()
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)) {
        UpperBar(title = "Crear paciente")
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
                    text = "Información General",
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondary
                )
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("Nombre") })
                OutlinedTextField(
                    value = paternalLastName,
                    onValueChange = { paternalLastName = it },
                    label = { Text("Apellido paterno") })
                OutlinedTextField(
                    value = maternalLastName,
                    onValueChange = { maternalLastName = it },
                    label = { Text("Apellido materno") }
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
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") })
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Celular") })
            }

        }
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
                    text = "Información Energética",
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondary
                )
                OutlinedTextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("Calorías") })
                OutlinedTextField(
                    value = carbs,
                    onValueChange = { carbs = it },
                    label = { Text("Carbohidratos (%)") })
                OutlinedTextField(
                    value = protein,
                    onValueChange = { protein = it },
                    label = { Text("Proteinas (%)") })
                OutlinedTextField(
                    value = lipids,
                    onValueChange = { lipids = it },
                    label = { Text("Lípidos (%)") })
            }

        }
        Button(
            onClick = {
                patientViewModel.addNewPatient(
                    Patient(
                        id = UUID.randomUUID().toString(),
                        firstName = firstName,
                        paternalLastName = paternalLastName,
                        maternalLastName = maternalLastName,
                        birthDate = selectedDate.asDate(),
                        email = email,
                        phoneNumber = phoneNumber,
                        firstTime = true,
                        memberSince = Calendar.getInstance().time,
                        profilePictureURL = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
                        energyRequirements = EnergyRequirements(
                            calories = calories.toInt(),
                            carbohydratePercentage = carbs.toInt(),
                            proteinPercentage = protein.toInt(),
                            lipidPercentage = protein.toInt()
                        ),

                        )
                )
                firstName = ""
                paternalLastName = ""
                maternalLastName = ""
                selectedDate = ""
                email = ""
                phoneNumber = ""
                calories = ""
                carbs = ""
                protein = ""
                lipids = ""


                Toast
                    .makeText(
                        context,
                        "Paciente Creado",
                        Toast.LENGTH_SHORT
                    ).show()

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text("Crear")
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
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CreatePatientScreenPreview() {
    NutritivappTheme {
        CreatePatientScreen(PatientViewModel())
    }
}