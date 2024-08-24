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
import com.excal.simplelogin.data.UserEntity
import com.excal.simplelogin.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding


    private val registerViewModel:RegisterViewModel by viewModels{
        RegisterViewModelFactory(UserDatabase.getInstance(applicationContext).userDao())

    } //Inisialisasi RegisterViewModelFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)








        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tvLogin.setOnClickListener {
            val intentLoginActivity=Intent(this, LoginActivity::class.java)
            startActivity(intentLoginActivity)
        }


        //Disini kita mulai logic buat register
        binding.btnRegister.setOnClickListener{

            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            val password2=binding.etPassword2.text.toString()

            if(email.isEmpty()||password.isEmpty()||password2.isEmpty()){ //Pertama kita cek dulu apakah input kosong
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else {
                if(password!=password2){ //Lalu cek apakah password1 dan 2 sudah sama
                    Toast.makeText(this, "Password didn't match", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            registerViewModel.checkEmail(email) //Lalu kita cek apakah email sudah terdaftar
            //nanti value dari isEmailUsed yang ada di ViewModel berubah


    }




        registerViewModel.isEmailUsed.observe(this, Observer{ isUsed-> //Pada saat isEmailUsed berubah valuenya
            //Kode yang di bawah ini akan dijalankan
            if(isUsed){//Kita cek apakah email terpakai
                Toast.makeText(this,"Email already used",Toast.LENGTH_SHORT)

            }else if(!isUsed){
                //Kalau tidak kita register
                registerViewModel.registerUser(UserEntity(username=binding.etEmail.text.toString(),email=binding.etEmail.text.toString(),password=binding.etPassword.text.toString()))
            }
        })

        registerViewModel.registrationSuccess.observe(this, Observer { success -> //Nha disini juga dilakukan observe untuk registrationSuccess
            //Saat sukses kita akan diarahkan ke login
            //Nanti kalau Observer nya error, kamu Alt+Klik terus pilih yang lifecycle.Observer
            if (success) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }





}