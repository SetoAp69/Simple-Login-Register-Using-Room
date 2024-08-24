# Simple-Login-Register-Using-Room

Okay jadi ini merupakan contoh sistem login dan register sederhana menggunakan ROOM database
Langkah pembuatan : 

1. Persiapan Dependency, plugins, dan features
- Import dulu dependency buat Room, Coroutine, dan ViewModelScope lewat build.gradle module:app
- Aktifkan viewBinding dan dataBinding lewat buildFeatures
- tambahkan parcelize dan aktifkan kotlin kapt lewat plugins pada build.gradle

2. Persiapan database
   - Buat file kotlin DAO
   - Buat data class UserEntity
   - Buat kotlin class Database (dicontoh nama filenya UserDatabase tapi sebenarnya tidak terbatas hanya buat user)
   - Buat kotlin class UserRepository
     
4. Register
   - buat Activity untuk register beserta Designya
   - buat RegisterViewModelFactory dan RegisterViewModel
   - Buat RegisterActivity
5. Cek apakah database sudah berjalan:
   - Setelah register jadi coba run project
   - Untuk monitoring database kalian buka AppInspector yang ada dibawah android studio
     ![image](https://github.com/user-attachments/assets/f3ca20c4-e358-4bfd-a9f5-1086d4093708)

   - Lalu pilih perangkat yang dijalankan
     ![image](https://github.com/user-attachments/assets/094f8603-6817-4968-aff5-f41f7e4a33ea)

   - Coba lakukan register
   - Lalu pencet refresh pada App inspection
   - Apabila sukses maka data yang dimasukan akan muncul ke database

7. Buat login
   - Langkahnya sama kayak Register
  
  


  Catatan : 
  Pada saat koding kalian abaikan dulu import yang di atas kode, nanti apabila ada error dapat coba alt+enter untuk melakukan import otomatis
