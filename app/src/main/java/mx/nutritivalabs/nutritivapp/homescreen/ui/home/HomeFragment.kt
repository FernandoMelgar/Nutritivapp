package mx.nutritivalabs.nutritivapp.homescreen.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.databinding.FragmentHomeBinding
import mx.nutritivalabs.nutritivapp.databinding.FragmentPatientsBinding

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
        val root: View = binding.root

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