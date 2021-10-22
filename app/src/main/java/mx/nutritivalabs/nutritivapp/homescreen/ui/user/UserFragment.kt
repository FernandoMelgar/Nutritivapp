package mx.nutritivalabs.nutritivapp.homescreen.ui.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import mx.nutritivalabs.nutritivapp.MainActivity
import mx.nutritivalabs.nutritivapp.databinding.FragmentUserBinding
import mx.nutritivalabs.nutritivapp.homescreen.HomeScreen

class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private val mAuth = FirebaseAuth.getInstance()

    private lateinit var binding: FragmentUserBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding = FragmentUserBinding.inflate(layoutInflater)
        configurarEventos()

        return binding.root
    }

    private fun configurarEventos() {
        binding.btnLogOut.setOnClickListener{
            signOut()
        }
    }

    private fun signOut() {
        mAuth.signOut()
        val intentMainActivity = Intent(context, MainActivity::class.java)
        startActivity(intentMainActivity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}