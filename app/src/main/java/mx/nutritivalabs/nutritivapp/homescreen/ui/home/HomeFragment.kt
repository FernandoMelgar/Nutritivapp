package mx.nutritivalabs.nutritivapp.homescreen.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.platform.ViewCompositionStrategy.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.databinding.FragmentHomeBinding
import mx.nutritivalabs.nutritivapp.databinding.FragmentPatientsBinding
import mx.nutritivalabs.nutritivapp.ui.theme.NutritivappTheme
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root
        root.findViewById<ComposeView>(R.id.home_calendar_view).apply {
            setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                NutritivappTheme() {
                    DateCircleList(dateLabelList = createDateStringFromTodayTo(6))
                }
            }
        }

        root.findViewById<ComposeView>(R.id.home_meetings_view).apply {
            setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                NutritivappTheme() {
                    val today = Calendar.getInstance().time
                    val todayMeetings = DayMeetings(
                        today, listOf(
                            Meeting(today, "Patient1", "Zoom"),
                            Meeting(today, "Patient2", "Zoom"),
                            Meeting(today, "Patient3", "Zoom"),
                            Meeting(today, "Patient4", "Zoom"),
                            Meeting(today, "Patient5", "Zoom"),
                            Meeting(today, "Patient6", "Zoom"),
                            Meeting(today, "Patient7", "Zoom"),
                            Meeting(today, "Patient8", "Zoom")
                        )
                    )

                    NutritivappTheme() {
                        MeetingList(todayMeetings.list)
                    }
                }
            }
        }

        configureEvents()
        return root
    }

    private fun configureEvents() {
        binding.ivUserProfilePicture.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationConfiguration()
            findNavController().navigate(action)
        }

        binding.btnConfiguration.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationConfiguration()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}