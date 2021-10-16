package mx.nutritivalabs.nutritivapp.homescreen.ui.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mx.nutritivalabs.nutritivapp.databinding.FragmentPatientsBinding

class PatientsFragment : Fragment() {

    private lateinit var patientsViewModel: PatientsViewModel
    private var _binding: FragmentPatientsBinding? = null

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

        val textView: TextView = binding.textDashboard
        patientsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}