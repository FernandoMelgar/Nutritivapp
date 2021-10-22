package mx.nutritivalabs.nutritivapp.homescreen.ui.configuration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.databinding.ConfigurationFragmentBinding
import mx.nutritivalabs.nutritivapp.databinding.FragmentHomeBinding
import mx.nutritivalabs.nutritivapp.homescreen.ui.home.HomeFragmentDirections
import mx.nutritivalabs.nutritivapp.homescreen.ui.home.HomeViewModel

class ConfigurationFragment : Fragment() {

    private lateinit var homeViewModel: ConfigurationViewModel

    private var _binding: ConfigurationFragmentBinding? = null

    private val binding get() = _binding!!


    companion object {
        fun newInstance() = ConfigurationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ConfigurationFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        configureEvents()
        return root
    }

    private fun configureEvents() {
        _binding?.ivUserProfilePicture?.setOnClickListener {
            val action = ConfigurationFragmentDirections.actionNavigationConfigurationToNavigationUser()
            findNavController().navigate(action)
        }

        _binding?.tvViewProfile?.setOnClickListener {
            val action = ConfigurationFragmentDirections.actionNavigationConfigurationToNavigationUser()
            findNavController().navigate(action)
        }

        _binding?.tvUserName?.setOnClickListener {
            val action = ConfigurationFragmentDirections.actionNavigationConfigurationToNavigationUser()
            findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}