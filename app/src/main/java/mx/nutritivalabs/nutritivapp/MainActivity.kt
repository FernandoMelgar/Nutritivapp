package mx.nutritivalabs.nutritivapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mx.nutritivalabs.nutritivapp.compose.*
import mx.nutritivalabs.nutritivapp.compose.meetings.CreateMeetingScreen
import mx.nutritivalabs.nutritivapp.compose.meetings.MeetingViewModel
import mx.nutritivalabs.nutritivapp.compose.navigation.BottomNavbar
import mx.nutritivalabs.nutritivapp.compose.navigation.NavigationItem
import mx.nutritivalabs.nutritivapp.compose.patient.PatientViewModel
import mx.nutritivalabs.nutritivapp.compose.patient.PatientsScreen
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme
import java.util.*


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val meetingViewModel = MeetingViewModel()
    val patientViewModel: PatientViewModel = PatientViewModel()

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
                    meetingViewModel.findMeetings(Calendar.getInstance().time, 1)
                    ScheduleScreen(
                        navController,
                        meetingViewModel
                    )
                }
                composable(route = NavigationItem.Patients.route) {
                    PatientsScreen(patientViewModel::addNewPatient)
                }
                composable(route = NavigationItem.Settings.route) {
                    SettingsScreen()
                }
                composable(NavigationItem.Meeting.route, arguments =
                listOf(
                    navArgument("id") {
                        type = NavType.StringType
                    }
                )) {
                    val id = it.arguments?.getString("id")
                    meetingViewModel.findByID(id!!)
                    patientViewModel.findById("4bfd3a6d-df8a-421b-8498-5600fc8e81cf")
                    MeetingScreen(
                        onPatientInfo = {
                            navController.navigate(
                                NavigationItem.Patient.withId("4bfd3a6d-df8a-421b-8498-5600fc8e81cf")
                            )
                        },
                        navController = navController,
                        meetingViewModel = meetingViewModel,
                        patientViewModel = patientViewModel
                    )

                }
                composable(NavigationItem.Patient.route, arguments = listOf(
                    navArgument("id") {
                        type = NavType.StringType
                    }
                )) {
                    val id = it.arguments?.getString("id")
                    patientViewModel.findById(id!!)
                    PatientScreen(viewModel = patientViewModel)
                }

                composable(NavigationItem.CreateMeeting.route) {
                    CreateMeetingScreen(navController, meetingViewModel)
                }

            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AppPreview() {
    NutritivappTheme() {
        App()
    }
}