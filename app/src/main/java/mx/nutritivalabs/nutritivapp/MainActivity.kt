package mx.nutritivalabs.nutritivapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import mx.nutritivalabs.nutritivapp.databinding.ActivityMainBinding
import mx.nutritivalabs.nutritivapp.homescreen.HomeScreen

class MainActivity : AppCompatActivity() {

    // Vals para Signin
    private val CODIGO_SIGNIN: Int = 500
    private val mAuth = FirebaseAuth.getInstance()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarEventos()
    }

    override fun onStart() {
        super.onStart()
        val usuario = mAuth.currentUser
        if (usuario != null){                               // El usuario está dentro, se le da la bienvenida
            binding.btnEntrar.visibility = View.GONE        // y accede al HomeScreen
            println("INICIA: ${usuario?.displayName}")
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    val intentHomeScreen = Intent(this, HomeScreen::class.java)
                    startActivity(intentHomeScreen)
                },
                1000
            )
        } else {
            println("Hacer SignIn")
        }
    }

    private fun autenticar() {
        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            CODIGO_SIGNIN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODIGO_SIGNIN){
            when (resultCode) {
                RESULT_OK -> {
                    val usuario = FirebaseAuth.getInstance().currentUser
                    println("Bienvenido: ${usuario?.displayName}")
                    println("Correo: ${usuario?.email}")
                    println("UID: ${usuario?.uid}")
                    val intentHomeScreen = Intent(this, HomeScreen::class.java)
                    startActivity(intentHomeScreen)
                }
                RESULT_CANCELED -> {
                    println("Cancelado...")
                    val response = IdpResponse.fromResultIntent(data)
                    println("Error: ${response?.error?.localizedMessage}")
                }
                else -> {
                    val response = IdpResponse.fromResultIntent(data)
                    println("Error: ${response?.error?.localizedMessage}")
                }
            }
        }
    }

    private fun configurarEventos() {
        binding.btnEntrar.setOnClickListener {
            autenticar()
        }
    }
}