package mx.nutritivalabs.nutritivapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import mx.nutritivalabs.nutritivapp.compose.patient.CreatePatientScreen
import mx.nutritivalabs.nutritivapp.compose.patient.PatientViewModel
import mx.nutritivalabs.nutritivapp.compose.patient.PatientsScreen
import mx.nutritivalabs.nutritivapp.compose.user.UserSettingsScreen
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
                        NavigationItem.UserSetting
                    ), navController
                )
            }
        ) {
            Box(modifier = Modifier.padding(
                PaddingValues(0.dp, 0.dp, 0.dp, it.calculateBottomPadding()))) {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationItem.Schedule.route
                    ) {
                        composable(route = NavigationItem.Schedule.route) {
                            meetingViewModel.findMeetings(Calendar.getInstance().time.simpleDateFormat(), 1)
                            ScheduleScreen(
                                navController,
                                meetingViewModel,
                                patientViewModel
                            )
                        }
                        composable(route = NavigationItem.Patients.route) {
                            patientViewModel.findAll()
                            PatientsScreen(
                                patientViewModel = patientViewModel,
                                onPatientInfo = {
                                    navController.navigate(
                                        NavigationItem.Patient.withId(it)
                                    )
                                },
                                onPatientCreate = {
                                    navController.navigate(NavigationItem.CreatePatient.route)
                                })
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
//                    meetingViewModel.findByID(id!!)
                            MeetingScreen(
                                onPatientInfo = {
                                    navController.navigate(
                                        NavigationItem.Patient.withId(patientViewModel.state.value.patient?.id!!)
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
                            PatientDetailScreen(viewModel = patientViewModel)
                        }

                        composable(NavigationItem.CreateMeeting.route) {
                            patientViewModel.findAll()
                            CreateMeetingScreen(navController, meetingViewModel, patientViewModel)
                        }
                        composable(NavigationItem.UserSetting.route) {
                            UserSettingsScreen()
                        }
                        composable(NavigationItem.CreatePatient.route) {
                            CreatePatientScreen(patientViewModel)
                        }
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