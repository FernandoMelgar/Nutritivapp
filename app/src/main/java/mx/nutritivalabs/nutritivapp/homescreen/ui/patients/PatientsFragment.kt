package mx.nutritivalabs.nutritivapp.homescreen.ui.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import mx.nutritivalabs.nutritivapp.databinding.FragmentPatientsBinding
import mx.nutritivalabs.nutritivapp.patient.Patient

class PatientsFragment : Fragment(), RowListener {

    private lateinit var patientsViewModel: PatientsViewModel
    private var _binding: FragmentPatientsBinding? = null

    private val patientAdapter = PatientAdapter(arrayListOf())

    private val args: PatientsFragmentArgs by navArgs<PatientsFragmentArgs>()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        patientsViewModel =
            ViewModelProvider(this).get(PatientsViewModel::class.java)

        _binding = FragmentPatientsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        configureAdapter()
        configureEvents()
        configureObservers()

        patientsViewModel.updatePatientsList(args.patient)

        return root
    }

    private fun configureObservers() {
        patientsViewModel.patientsListLive.observe(viewLifecycleOwner){list->
            patientAdapter.updateData(list)
        }
    }

    private fun configureEvents() {
        binding.fabNewPatient.setOnClickListener {
            val action = PatientsFragmentDirections.actionNavigationPatientsToAddPatientFragment()
            findNavController().navigate(action)
        }
    }

    private fun configureAdapter() {
        binding.rvPatient.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = patientAdapter
        }
        patientAdapter.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun rowClick(position: Int) {
        val selectedPatient = patientAdapter.patientArray[position]
        val action = PatientsFragmentDirections.actionNavigationPatientsToPatientDetailFragment(selectedPatient)
        findNavController().navigate(action)
    }
}