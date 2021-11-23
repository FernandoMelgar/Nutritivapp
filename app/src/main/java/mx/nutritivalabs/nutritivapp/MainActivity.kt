package mx.nutritivalabs.nutritivapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mx.nutritivalabs.nutritivapp.compose.*
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val meetingViewModel = MeetingViewModel()
    val patientViewModel = PatientViewModel()

    NutritivappTheme() {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                BottomNavbar(
                    listOf(
                        NavigationItem.Schedule,
                        NavigationItem.Patients,
                        NavigationItem.Settings
                    ), navController
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = false,
            floatingActionButton = {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {},
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(Icons.Filled.Add, "", tint = MaterialTheme.colors.onPrimary)
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.Schedule.route
            ) {
                composable(route = NavigationItem.Schedule.route) {
                    ScheduleScreen(navController, MeetingViewModel())
                }
                composable(route = NavigationItem.Patients.route) {
                    PatientsScreen()
                }
                composable(route = NavigationItem.Settings.route) {
                    SettingsScreen()
                }
                composable(NavigationItem.Meeting.route, arguments =
                listOf(
                    navArgument("id") {
                        type = NavType.LongType
                    }
                )) {
                    val id = it.arguments?.getLong("id")

                    val meeting = meetingViewModel.findByID(id!!)
                    val lastTenMeetings = meetingViewModel.findLastTen(meeting.patientId!!)
                    val patient = patientViewModel.findById(meeting.patientId)

                    MeetingScreen(
                        onPatientInfo = { navController.navigate(NavigationItem.Patient.withId(meeting.patientId)) },
                        navController = navController,
                        meeting = meeting,
                        lastTenMeetings = lastTenMeetings,
                        patient = patient
                    )

                }
                composable(NavigationItem.Patient.route, arguments = listOf(
                    navArgument("id") {
                        type = NavType.LongType
                    }
                )) {
                    val id = it.arguments?.getLong("id")
                    PatientScreen(viewModel = PatientViewModel(), patientId = id!!)
                }


            }
        }

    }
}

@Preview
@Composable
fun AppPreview() {
    NutritivappTheme() {
        App()
    }
}