package mx.nutritivalabs.nutritivapp.homescreen.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mx.nutritivalabs.nutritivapp.Screen
import mx.nutritivalabs.nutritivapp.compose.MeetingViewModel
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme

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
                            ScheduleScreen(navController, MeetingViewModel())
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
    }
}

