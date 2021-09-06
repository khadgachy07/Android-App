//package com.khadga.meromedapp
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.CheckBox
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.khadga.meromedapp.api.ServiceBuilder
//import com.khadga.meromedapp.db.ProductDB
//import com.khadga.meromedapp.notification.NotificationSender
//import com.khadga.meromedapp.repository.UserRepository
//import com.khadga.meromedapp.ui.home.HomeFragment
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Dispatchers.Main
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class LoginActivity : AppCompatActivity() {
//
//    private lateinit var etUsername: EditText
//    private lateinit var etPassword: EditText
//    private lateinit var btnSignUp: Button
//    private lateinit var btnLogIn: Button
//    private lateinit var chkSaveProfile: CheckBox
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        etUsername = findViewById(R.id.etUsername)
//        etPassword = findViewById(R.id.etPassword)
//        btnSignUp = findViewById(R.id.btnSignUp)
//        btnLogIn = findViewById(R.id.btnLogIn)
//        chkSaveProfile = findViewById(R.id.chkSaveProfile)
//
//        btnSignUp.setOnClickListener {
//            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
//            startActivity(intent)
//
//        }
//
//        btnLogIn.setOnClickListener {
//            login()
//            saveProfile()
//        }
//
//    }
//
//    private fun saveProfile() {
//        if (chkSaveProfile.isChecked){
//            saveSharedPref()
//        }
//    }
//
//
//    private fun login() {
//        val username = etUsername.text.toString()
//        val password = etPassword.text.toString()
//
//        CoroutineScope(Dispatchers.IO).launch {
//        try {
//
////        var user: User? = null
////        CoroutineScope(Dispatchers.IO).launch {
////            user = UserDB.getInstance(this@LoginActivity)
////                .getUserDAO()
////            .checkUser(username, password)
//
//            val ur = UserRepository()
//            val response = ur.loginUser(username, password)
////            val dao = ProductDB.getInstance(this@LoginActivity).getUserDAO()
//            if (response.success == true) {
//                NotificationSender(this@LoginActivity,"Logged In ","${etUsername.text.toString()}").createHighPriority()
//
//                ServiceBuilder.token = "Bearer " + response.token
////                withContext(Main) {
////                    //Toast.makeText(this@LoginActivity, response.data.toString(), Toast.LENGTH_LONG).show()
////                }
////                dao.deleteUser()
////                dao.registerUser(response.data!!)
//                val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
//                startActivity(intent)
//                withContext(Main) {
//                    //startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
//                    Toast.makeText(this@LoginActivity, "Welcome $username", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                withContext(Main) {
//                    Toast.makeText(this@LoginActivity, "Invalid Cerdentials", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//        } catch (ex: Exception) {
//            println(ex.printStackTrace())
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@LoginActivity, "Invalid ${ex}", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
////            if (user == null) {
////                withContext(Dispatchers.Main) {
////                    Toast.makeText(this@LoginActivity, "Invalid credentials.", Toast.LENGTH_SHORT)
////                        .show()
////                }
////            } else {
////                withContext(Dispatchers.Main) {
////                startActivity(
////                        Intent(
////                            this@LoginActivity,
////                            DashboardActivity::class.java
////                        )
////                    )
////                }
////            }
//
//    }
//
//    private fun saveSharedPref() {
//        val username = etUsername.text.toString()
//        val password = etPassword.text.toString()
//        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
//        val editor = sharedPref.edit()
//        editor.putString("username", username)
//        editor.putString("password", password)
//        editor.apply()
//        Toast.makeText(this@LoginActivity, "Username and Password are saved.", Toast.LENGTH_SHORT)
//            .show()
//    }
//}