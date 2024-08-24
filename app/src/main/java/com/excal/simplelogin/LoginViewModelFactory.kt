package com.excal.simplelogin

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.excal.simplelogin.data.UserDao
import com.excal.simplelogin.data.UserRepository

class LoginViewModelFactory(private val userDao: UserDao):ViewModelProvider.Factory {

    //Sama kayak RegisterViewModel Factory diubah dikit
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(UserRepository(userDao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}