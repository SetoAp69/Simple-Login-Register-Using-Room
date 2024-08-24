package com.excal.simplelogin.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//Entity merupakan tabel yang ada di database
//jadi kalau nanti butuh tabel lain, kalian perlu buat Entitiy lagi
@Entity(tableName="user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val uid:Int =0, //Ini primary key, Auto increment tidak perlu diinput pada saat create


    @ColumnInfo(name ="username") val username:String?, //Ini Username

    @ColumnInfo(name ="email") val email:String?, //Ini Email

    @ColumnInfo(name="password") val password:String? //Password
)