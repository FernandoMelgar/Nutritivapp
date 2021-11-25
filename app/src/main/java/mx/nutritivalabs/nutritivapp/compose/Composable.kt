package mx.nutritivalabs.nutritivapp.compose

import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.rememberImagePainter
import mx.nutritivalabs.nutritivapp.R
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
fun PatientInfoChip(modifier: Modifier = Modifier, imgUrl: String, fullName: String, patientSince: String) {
    Surface(
        modifier = modifier,
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


@Composable
fun DisplayInfoSection(title: String, data: Map<String, Any>) {
    Surface(
        modifier = Modifier.padding(16.dp),
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
            for ((key, value) in data) {
                InfoChip(title = key, value.toString())
            }
        }
    }

}

@Composable
fun CustomDropdownMenu(
    options: Map<String, String>,
    onSelected: (String, String) -> Unit,
    label: String,
    value: String
) {
    var expanded by remember { mutableStateOf(false) }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedText by remember { mutableStateOf("") }
    var selectedId by remember { mutableStateOf("") }
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {
                selectedText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            for ((k, v) in options){
                DropdownMenuItem(onClick = {
                    onSelected(v, k)
                    selectedText = k
                    expanded = false
                }) {
                    Text(text = k)
                }
            }
        }
    }

}

@Composable
fun GreetingSection(onProfileClick: () -> Unit) {

    val userName = "Lilly Collins"
    Surface() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.userlilly),
                    contentDescription = "User picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .clickable { onProfileClick() }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = userName,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Glad to have you back!",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }

    }

}
