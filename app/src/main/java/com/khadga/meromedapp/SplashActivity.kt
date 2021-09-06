//package com.khadga.meromedapp
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.khadga.meromedapp.api.ServiceBuilder
//import com.khadga.meromedapp.db.ProductDB
//import com.khadga.meromedapp.repository.UserRepository
//import kotlinx.coroutines.*
//
//class SplashActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            delay(3000)
//            val myPref = getSharedPreferences("MyPref", MODE_PRIVATE)
//            val username = myPref.getString("username", "")
//            val password = myPref.getString("password", "")
//            try {
//                if (username == "" || password == "") {
//                    startActivity(
//                        Intent(
//                            this@SplashActivity,
//                            LoginActivity::class.java
//                        )
//                    )
//                    finish()
//                    return@launch
//                }
//                val ur = UserRepository()
//                val response = ur.loginUser(username!!, password!!)
//                val dao = ProductDB.getInstance(this@SplashActivity).getUserDAO()
//                if (response.success == true) {
//                    ServiceBuilder.token = "Bearer " + response.token
//                    withContext(Dispatchers.Main) {
////                        Toast.makeText(this@SplashActivity, response.data.toString(), Toast.LENGTH_LONG).show()
//                    }
//                    dao.deleteUser()
//                    dao.registerUser(response.data!!)
//                    startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
//                    withContext(Dispatchers.Main) {
////                        Toast.makeText(this@SplashActivity, "Welcome $username", Toast.LENGTH_SHORT)
////                            .show()
//                        startActivity(
//                            Intent(
//                                this@SplashActivity,
//                                DashboardActivity::class.java
//                            )
//                        )
//                        finish()
//                    }
//                } else {
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(
//                            this@SplashActivity,
//                            "Invalid Cerdentials",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        startActivity(
//                            Intent(
//                                this@SplashActivity,
//                                LoginActivity::class.java
//                            )
//                        )
//                        finish()
//                    }
//                }
//
//            } catch (ex: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@SplashActivity, "Invalid ${ex}", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//        }
//    }
//}