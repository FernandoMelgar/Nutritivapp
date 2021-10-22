package mx.nutritivalabs.nutritivapp.homescreen.ui.add_patient

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.databinding.AddPatientFragmentBinding
import mx.nutritivalabs.nutritivapp.databinding.FragmentHomeBinding
import mx.nutritivalabs.nutritivapp.patient.Patient

class AddPatientFragment : Fragment() {

    companion object {
        fun newInstance() = AddPatientFragment()
    }

    private lateinit var viewModel: AddPatientViewModel

    private var _binding: AddPatientFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AddPatientFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        configureObservers()
        configureEvents()


        return root
    }

    private fun configureEvents() {
        binding.btnRegisterPatient.setOnClickListener {
            val patient: Patient
            val id = 0
            val name = binding.etName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val secondLastName = binding.etSecondLastName.text.toString()
            patient = Patient(id, name, lastName, secondLastName)
//            viewModel.addPatient(patient)
            println("paciente añadido")
        }
    }

    private fun configureObservers() {

    }



}