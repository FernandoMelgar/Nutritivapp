package mx.nutritivalabs.nutritivapp.compose

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import mx.nutritivalabs.nutritivapp.compose.patient.PatientViewModel
import mx.nutritivalabs.nutritivapp.simpleDateFormat
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme
import mx.nutritivalabs.nutritivapp.ui.theme.black

@Composable
fun PatientDetailScreen(viewModel: PatientViewModel) {
    val state = viewModel.state.value
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        UpperBar("Paciente")
        if (state.patient != null) {
            PatientInfoChip(
                state.patient.profilePictureURL,
                state.patient.fullName,
                state.patient.memberSince?.simpleDateFormat() ?: ""
            )
            DisplayInfoSection(
                "Información General",
                mapOf(
                    "Fecha de Nacimiento" to (state.patient.birthDate?.simpleDateFormat() ?: ""),
                    "Email" to (state.patient.email ?: ""),
                    "Teléfono" to (state.patient.phoneNumber ?: "")
                )
            )
            DisplayInfoSection("Datos Clínicos", state.patient.clinicData)
            GettingInContact()
            Spacer(Modifier.height(120.dp))
        }

    }
}




@Composable
fun InfoChip(title: String, body: String) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {
        Text(title, fontWeight = FontWeight.Bold, color = Color.Gray)
        Text(body, color = black.copy(alpha = .84f))
    }

}

@Composable
private fun GettingInContact() {

    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { },
        ) {
            Text("Mensaje", color = MaterialTheme.colors.onSecondary)
        }
        Button(onClick = {
            val callIntent: Intent = Uri.parse("tel:5551234").let { number ->
                Intent(Intent.ACTION_DIAL, number)
            }
            ActivityCompat.startActivity(context, callIntent, null)
        }

        ) {
            Text("Llamar", color = MaterialTheme.colors.onSecondary)
        }
    }
}


@Preview
@Composable
fun GeneralInfoPreview() {
    NutritivappTheme() {
        PatientInfoChip("", "Fernando Manuel Melgar Fuentes", "18/09/2021")
    }
}



@Composable
fun InfoChipPreview() {
    NutritivappTheme() {
        InfoChip(title = "Nombre", body = "Fernando Manuel Melgar Fuentes")
    }
}


@Preview
@Composable
fun PreviewP1() {
    NutritivappTheme() {
        Column {
            UpperBar("Paciente")
            PatientInfoChip("", "Fernando Manuel Melgar Fuentes", "18/09/2021")
            DisplayInfoSection(
                title = "Requerimientos energéticos",
                data = mockEnergyRequirementsData()
            )
            DisplayInfoSection("Requerimientos energéticos", mockEnergyRequirementsData())
            DisplayInfoSection("Datos antropométricos", mockAnthropometricData())
            DisplayInfoSection("Datos Clínicos", mockClinicData())
            DisplayInfoSection("Dietéticos", mockDieteticData())
            DisplayInfoSection("Estilo de vida", mockLifeStyleData())
        }
    }
}


private fun mockEnergyRequirementsData(): Map<String, String> {
    return mapOf(
        "Calorias" to "2200",
        "Carbohidratos" to "40%",
        "Lípidos" to "30%",
        "Proteinas" to "30%"
    )
}


fun mockAnthropometricData(): Map<String, String> {
    return mapOf(
        "Talla" to "176",
        "Grasa" to "18%",
        "Agua" to "71%",
        "Musculo" to "22 %",
    )
}

fun mockClinicData(): Map<String, String> {
    return mapOf(
        "Patologías" to "No hay patologías",
        "Signos" to "No hay problemas",
        "Antecedentes" to "No hay antecedentes"
    )
}

fun mockDieteticData(): Map<String, String> {
    return mapOf(
        "Alergias" to "No hay alergias",
        "Intolerancias" to "No hay intolerancias",
        "Gustos" to "Frutas, yogurt, cereales, avena, sushi, comida crujiente"
    )
}

fun mockLifeStyleData(): Map<String, String> {
    return mapOf(
        "Hídrico" to "2450 ml",
        "Ejercicio" to "Gym 5 veces a la semana, 1:30hrs en promedio",
        "Toxicomanías" to "",
        "Sueño" to "Baja"
    )
}