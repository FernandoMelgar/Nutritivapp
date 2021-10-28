package mx.nutritivalabs.nutritivapp.homescreen.ui.add_patient

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.databinding.AddPatientFragmentBinding
import mx.nutritivalabs.nutritivapp.databinding.FragmentHomeBinding
import mx.nutritivalabs.nutritivapp.patient.Patient

class AddPatientFragment : Fragment() {

    companion object {
        fun newInstance() = AddPatientFragment()
    }

    private val viewModel: AddPatientViewModel by viewModels()
    private lateinit var binding: AddPatientFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddPatientFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureObservers()
        configureEvents()
    }

    private fun configureEvents() {
        binding.btnRegisterPatient.setOnClickListener {
            val action = AddPatientFragmentDirections.actionAddPatientFragmentToNavigationPatients()
                .setPatient(createPatient())
            findNavController().navigate(action)
        }
    }

    private fun createPatient(): Patient {
        val patient: Patient
        val id = 0
        val name = binding.etName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val secondLastName = binding.etSecondLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val phoneNumer = binding.etPhoneNumber.text.toString()
        patient = Patient(id, name, lastName, secondLastName, email = email, phoneNumber = phoneNumer)
        return patient
    }

    private fun configureObservers() {

    }



}