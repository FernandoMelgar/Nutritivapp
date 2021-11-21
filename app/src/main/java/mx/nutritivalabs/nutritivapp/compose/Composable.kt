package mx.nutritivalabs.nutritivapp.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme


@Composable
fun UpperBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .background(MaterialTheme.colors.primary),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
fun PreviewUpperBar() {
    NutritivappTheme() {
        UpperBar(title = "Upper bar")
    }
}


@Composable
fun PatientInfoChip(imgUrl: String, fullName: String, patientSince: String) {
    Surface(
        modifier = Modifier.padding(16.dp),
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = imgUrl,
                    builder = {
                        crossfade(true)
                    }),
                contentDescription = "Patient profile pic",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(82.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
            )
            Spacer(Modifier.width(8.dp))
            Column {
                Text(fullName)
                Text(
                    "Paciente desde: $patientSince",
                    color = MaterialTheme.colors.onSurface.copy(alpha = .6f)
                )
            }
        }
    }
}
