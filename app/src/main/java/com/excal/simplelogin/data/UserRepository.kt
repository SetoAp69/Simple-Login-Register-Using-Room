package com.excal.simplelogin.data

class UserRepository(private val dao:UserDao) {

//Repository digunakan untuk menghubungkan DAO dengan aplikasi yang dubuat
    //Biasanya Repository berisi variabel dan function yang mengembalikan data dari DAO


    val users = dao.getAllUsers()
    suspend fun insert(user:UserEntity){
        return dao.insert(user)
    }

    suspend fun getEmail(email:String):UserEntity?{
        return dao.getEmail(email)
    }
    suspend fun isEmailUsed(email: String): Boolean {
        return dao.getEmail(email) != null
    }

    suspend fun auth(email:String,password:String):Boolean{
        return dao.auth(email,password)!=null
    }


}