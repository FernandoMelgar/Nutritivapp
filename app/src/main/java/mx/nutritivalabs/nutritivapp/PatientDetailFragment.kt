package mx.nutritivalabs.nutritivapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PatientDetailFragment : Fragment() {

    companion object {
        fun newInstance() = PatientDetailFragment()
    }

    private lateinit var viewModel: PatientDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.patient_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}