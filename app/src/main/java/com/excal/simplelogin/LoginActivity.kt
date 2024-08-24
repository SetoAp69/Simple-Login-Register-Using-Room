package com.excal.simplelogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.excal.simplelogin.data.UserDatabase
import com.excal.simplelogin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding



    private val loginViewModel:LoginViewModel by viewModels{
        LoginViewModelFactory(UserDatabase.getInstance(applicationContext).userDao())


    }//Inisialisasi LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnLogin.setOnClickListener{ //Login button
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()

            loginViewModel.auth(email,password)
        }
        binding.tvRegister.setOnClickListener{ //Register Button
            var regIntent=Intent(this,RegisterActivity::class.java)
            startActivity(regIntent)
        }

        loginViewModel.authSuccess.observe(this, Observer{success->

            //Saat sukses kita akan diarahkan ke main activity
            //Nanti kalau Observer nya error, kamu Alt+Klik terus pilih yang lifecycle.Observer

            if(success){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Invalid Account", Toast.LENGTH_SHORT).show()

            }
        })
    }
}