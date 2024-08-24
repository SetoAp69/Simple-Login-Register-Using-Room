package com.excal.simplelogin

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.excal.simplelogin.data.UserRepository
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginViewModel(private val userRepository:UserRepository): ViewModel() {

    //View Model digunakan untuk mengolah data yang diperlukan pada UI

    //Data pada database pada umumnya perlu dicek secara berkala, maka kita memerlukan yang namanya LiveData atau MutableLiveData
    //Nanti Pengecekan data dilakukan menggunakan perintah Observe yang diterapkan di Activity

    private val _authSuccess=MutableLiveData<Boolean>()
    val authSuccess: LiveData<Boolean> get()=_authSuccess
    //Nha pada saat value dari variable authSuccess berubah,
    //maka activity yang melakukan observe ke var ini akan diberi sinyal dan akan menjalankan kode yang ada

    fun auth(email:String,password:String){

        viewModelScope.launch(Dispatchers.IO){
            val auth=userRepository.auth(email,password)
            withContext(Dispatchers.Main){
                _authSuccess.value=auth
            }
        }
    }

}