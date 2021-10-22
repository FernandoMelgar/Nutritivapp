package mx.nutritivalabs.nutritivapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.nutritivalabs.nutritivapp.databinding.ActivityMainBinding
import mx.nutritivalabs.nutritivapp.homescreen.HomeScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarEventos()
    }

    private fun configurarEventos() {
        binding.btnEntrar.setOnClickListener {
            val intentHomeScreen = Intent(this, HomeScreen::class.java)
            startActivity(intentHomeScreen)
        }
    }
}