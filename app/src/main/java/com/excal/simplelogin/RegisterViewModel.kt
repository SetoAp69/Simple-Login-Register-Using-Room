package com.excal.simplelogin

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.excal.simplelogin.data.UserEntity
import com.excal.simplelogin.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//View Model digunakan untuk mengolah data yang diperlukan pada UI

//Data pada database pada umumnya perlu dicek secara berkala, maka kita memerlukan yang namanya LiveData atau MutableLiveData
//Nanti Pengecekan data dilakukan menggunakan perintah Observe yang diterapkan di Activity


class RegisterViewModel(private val userRepository: UserRepository): ViewModel(){
    private val _isEmailUsed=MutableLiveData<Boolean>()
    val isEmailUsed: LiveData<Boolean>get()=_isEmailUsed
    //Nha pada saat value dari variable isEmailUsed berubah,
    //maka activity yang melakukan observe ke var ini akan diberi sinyal dan akan menjalankan kode yang ada


    private val _registrationSuccess = MutableLiveData<Boolean>()
    val registrationSuccess:LiveData<Boolean>get()=_registrationSuccess


    fun checkEmail(email:String){ //Digunakan untuk cek apakah email sudah terdaftar
        viewModelScope.launch(Dispatchers.IO){
            val emailAlreadyUsed=userRepository.isEmailUsed(email)
            withContext(Dispatchers.Main){
                _isEmailUsed.value = emailAlreadyUsed

            }

        }
    }

    fun registerUser(user: UserEntity){ //Digunakan untuk menambahkan akun baru
        viewModelScope.launch(Dispatchers.IO){

            userRepository.insert(user)
            withContext(Dispatchers.Main){
                _registrationSuccess.value=true
            }
        }
    }
}