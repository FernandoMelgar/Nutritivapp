package mx.nutritivalabs.nutritivapp.compose.navigation

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme
import mx.nutritivalabs.nutritivapp.ui.theme.black

sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    object Schedule : NavigationItem("schedule-screen", Icons.Filled.Home, "Schedule")
    object Patients : NavigationItem("patients-screen", Icons.Filled.Person, "Patients")
    object Settings : NavigationItem("settings-screen", Icons.Filled.Settings, "Settings")
    object Patient: NavigationItem("patients/{id}",Icons.Filled.Person, "Patient") {
        fun withId(id: Long): String {
            return this.route.replace("{id}", id.toString())
        }
    }
    object Meeting: NavigationItem("meetings/{id}", Icons.Filled.ThumbUp, "Meetings"){
        fun withId(id: String): String {
            return this.route.replace("{id}", id)
        }
    }

    object CreateMeeting: NavigationItem("meetings/create", Icons.Filled.Home, "Create Meeting")
}
