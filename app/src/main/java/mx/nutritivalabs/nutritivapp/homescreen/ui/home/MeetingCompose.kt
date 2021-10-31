package mx.nutritivalabs.nutritivapp.homescreen.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.Screen

@Composable
fun MeetingDetailScreen(meetingId: Long?, navController: NavHostController) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IconButton(onClick = { navController.navigate(Screen.ScheduleScreen.route) }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
            Text("17 Noviembre 2021")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Paciente",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
        )
        Row(Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.userlilly),
                    contentDescription = null
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(220.dp)
            ) {
                Text("Nombre: Alison Brie")
                Spacer(modifier = Modifier.height(12.dp))
                Text("Meeting id: $meetingId")
                Text("Vegan")
            }
        }
        Text(
            "Información general",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(400.dp)
                .border(
                    1.5.dp,
                    MaterialTheme.colors.secondary,
                    RoundedCornerShape(8.dp)
                )
        ) {
            Text("Pequeñas notas sobre la última sesión")
        }
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
            Button(onClick = { }) {
                Text("Llamar", color = MaterialTheme.colors.onSecondary)
            }
        }
        Spacer(modifier = Modifier.height(120.dp))
    }
}