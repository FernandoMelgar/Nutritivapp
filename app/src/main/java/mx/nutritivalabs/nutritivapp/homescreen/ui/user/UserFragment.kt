package mx.nutritivalabs.nutritivapp.homescreen.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.nutritivalabs.nutritivapp.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding = FragmentUserBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}