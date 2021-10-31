package mx.nutritivalabs.nutritivapp.homescreen.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.Screen
import mx.nutritivalabs.nutritivapp.ui.theme.ButtonBlue
import mx.nutritivalabs.nutritivapp.ui.theme.LightRed
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme
import mx.nutritivalabs.nutritivapp.ui.theme.OrangeYellow1
import java.util.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                NutritivappTheme() {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "schedule"
                    ) {
                        composable(Screen.ScheduleScreen.route) {
                            ScheduleScreen(navController)
                        }
                        composable(Screen.Meeting.route, arguments =
                        listOf(
                            navArgument("id") {
                                type = NavType.LongType
                            }
                        )) {
                            val id = it.arguments?.getLong("id")
                            MeetingDetailScreen(id, navController)

                        }
                    }
                }
            }

        }
    }
}

