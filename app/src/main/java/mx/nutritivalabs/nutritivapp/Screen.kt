package mx.nutritivalabs.nutritivapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val displayName: String) {
    object ScheduleScreen : Screen(route = "schedule", displayName = "Schedule")
    object Meeting : Screen(route = "meetings/{id}", displayName = "Meeting Detail") {
        fun withId(id: Long) = this.route.replace("{id}", "$id")
    }
}
