package mx.nutritivalabs.nutritivapp.homescreen.ui.patient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import mx.nutritivalabs.nutritivapp.databinding.PatientDetailFragmentBinding
import mx.nutritivalabs.nutritivapp.homescreen.ui.patients.PatientsFragmentArgs


class PatientDetailFragment : Fragment() {

    companion object {
        fun newInstance() = PatientDetailFragment()
    }

    private val viewModel: PatientDetailViewModel by viewModels()
    private lateinit var binding: PatientDetailFragmentBinding
    private val args: PatientsFragmentArgs by navArgs<PatientsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PatientDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = args.patient?.fullName
        binding.tvObjective.text = "Objetivos: ---"    // TODO
        if (args.patient?.email != null){
            binding.tvEmail.text = "Correo: ${args.patient!!.email}"
        }
        if (args.patient?.phoneNumber != null){
            binding.tvPhoneNumber.text = "Teléfono: ${args.patient!!.phoneNumber}"
        }

        // configureEvents()
        // configureObservers()

    }

    private fun configureEvents() {
        // TODO("Not yet implemented")
    }

    private fun configureObservers() {
        // TODO("Not yet implemented")
    }
}