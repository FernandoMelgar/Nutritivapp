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
import mx.nutritivalabs.nutritivapp.homescreen.ui.home.MeetingScreenDetail
import mx.nutritivalabs.nutritivapp.homescreen.ui.home.ScheduleScreen
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
                    ScheduleScreen(navController)
                }
                composable(route = NavigationItem.Patients.route) {
                    PatientsScreen()
                }
                composable(route = NavigationItem.Settings.route) {
                    SettingsScreen()
                }
                composable(Screen.Meeting.route, arguments =
                    listOf(
                        navArgument("id") {
                        type = NavType.LongType
                    }
                )) {
                    val id = it.arguments?.getLong("id")
                    MeetingScreenDetail(id, navController)
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