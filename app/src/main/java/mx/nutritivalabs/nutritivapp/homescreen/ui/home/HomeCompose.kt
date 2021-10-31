package mx.nutritivalabs.nutritivapp.homescreen.ui.home

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.Screen
import mx.nutritivalabs.nutritivapp.homescreen.HomeScreen
import mx.nutritivalabs.nutritivapp.homescreen.ui.configuration.ConfigurationFragment
import mx.nutritivalabs.nutritivapp.ui.theme.ButtonBlue
import mx.nutritivalabs.nutritivapp.ui.theme.LightRed
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme
import mx.nutritivalabs.nutritivapp.ui.theme.OrangeYellow1
import java.util.*

@Composable
fun ScheduleScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column {
            GreetingSection()
            Spacer(modifier = Modifier.height(24.dp))
            CalendarSection()
            Spacer(modifier = Modifier.height(24.dp))
            MeetingSection(navController)
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}


@Composable
fun CalendarSection() {
    val calendarDays = mutableListOf<String>()
    for (i in 1..10)
        calendarDays.add(i.toString())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Calendar", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(18.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(calendarDays) { day ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.surface)
                        .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
                        .clickable { }
                ) {
                    Text(day, color = MaterialTheme.colors.onSurface)
                }
            }
        }
    }

}

@Composable
fun GreetingSection() {
    val context = LocalContext.current

    val userName = "Lilly Collins"
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

        Box(
            Modifier
                .clip(CircleShape)
                .clickable { }) {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Settings",
                tint = Color.Gray,
                modifier = Modifier
                    .size(32.dp)
            )
        }

    }
}

@Composable
fun MeetingChip(
    onClick: () -> Unit,
    patientName: String,
    desc: String,
    height: Int,
    color: Color
) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(height.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomStart
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                patientName,
                color = Color.White,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Text(
                desc, color = Color.White
            )

        }
    }
}


@Composable
fun MeetingSection(navController: NavHostController) {
    MeetingChip(
        patientName = "Rubén Villalpando",
        desc = "17:00 hrs - 17:40 hrs",
        height = 200,
        color = LightRed,
        onClick = { navController.navigate(Screen.Meeting.withId(1)) }
    )
//    Spacer(modifier = Modifier.height(12.dp))
    MeetingChip(
        patientName = "Arturo Marquez",
        desc = "18:00 hrs - 18:50 hrs",
        height = 100,
        color = ButtonBlue,
        onClick = { navController.navigate(Screen.Meeting.withId(2)) }
    )
//    Spacer(modifier = Modifier.height(12.dp))
    MeetingChip(
        patientName = "Víctor Sánchez",
        desc = "19:00 hrs - 19:50 hrs",
        height = 100,
        color = OrangeYellow1,
        onClick = { navController.navigate(Screen.Meeting.withId(3)) }
    )
}
