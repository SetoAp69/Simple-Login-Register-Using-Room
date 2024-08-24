package com.excal.simplelogin

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.excal.simplelogin.data.UserDao
import com.excal.simplelogin.data.UserRepository


//ViewModelFactory dibuat supaya ViewModel dapat memiliki parameter
//Sebagian besar kode ini cuma templat jadi cukup ikuti saja
class RegisterViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(UserRepository(userDao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}