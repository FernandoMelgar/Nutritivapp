package mx.nutritivalabs.nutritivapp.homescreen.ui.patients

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import mx.nutritivalabs.nutritivapp.databinding.PatientsListFragmentBinding

class PatientsListFragment : Fragment(), RowListener {

    companion object {
        fun newInstance() = PatientsListFragment()
    }

    private lateinit var viewModel: PatientsListViewModel
    private lateinit var binding: PatientsListFragmentBinding
    // private lateinit var _binding: FragmentPatientsListBinding
    private val patientsListAdapter = PatientsListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.patients_list_fragment, container, false)
        binding = PatientsListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientsListViewModel::class.java)

        configureList()    // RecyclerView
        // registrarObservadoes()
        configureEvents()
    }

    private fun configureEvents() {
        binding.fabAddPatient.setOnClickListener {
            //TODO iniciar proceso de agregar paciente
        }
    }

    private fun configureList() {
        binding.rvPatientsList.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = patientsListAdapter
        }
        patientsListAdapter.listener = this
    }

    override fun rowClick(position: Int) {
        val selectedPatient = patientsListAdapter.patientsArray[position]

        //val action = PatientsListFragmentDirections.action
    }
}