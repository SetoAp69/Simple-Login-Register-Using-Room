package com.excal.simplelogin.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


//DAO (Data Access Object) digunakan untuk berkomunikasi dengan sumber data
//Query untuk database dikelola melalui DAO ini

@Dao
interface UserDao{
    @Insert
    fun insert(user:UserEntity)

    @Query("Select*FROM user_table ORDER BY uid DESC")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("Delete FROM user_table")
    fun deleteAll():Int

    @Query("SELECT * FROM user_table Where email= :email")
    fun getEmail(email:String):UserEntity? //Buat ngecek udah kedaftar belum emailnya

    @Query("SELECT * FROM user_table Where email= :email AND password= :password")
    fun auth(email: String,password:String):UserEntity?  //Buat ngecek authentikasi kalau ditambah


}