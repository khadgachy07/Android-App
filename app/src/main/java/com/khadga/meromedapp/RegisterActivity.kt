//package com.khadga.meromedapp
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.khadga.meromedapp.model.AccountModel
//import com.khadga.meromedapp.repository.UserRepository
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class RegisterActivity : AppCompatActivity() {
//
//    private lateinit var etFullName: EditText
//    private lateinit var etUsername: EditText
//    private lateinit var etEmail: EditText
//    private lateinit var etPhone: EditText
//    private lateinit var etPassword: EditText
//    private lateinit var etConfirmPass: EditText
//    private lateinit var btnRegister: Button
//    private lateinit var btnSignIn: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//
//        binding()
//
//        btnRegister.setOnClickListener {
//            val fullName = etFullName.text.toString()
//            val username = etUsername.text.toString()
//            val email = etEmail.text.toString()
//            val phone = etPhone.text.toString()
//            val password = etPassword.text.toString()
//            val confirmPass = etConfirmPass.text.toString()
////            val userType = "Admin"
//
//            val intent = Intent(
//                this@RegisterActivity,
//                LoginActivity::class.java
//            )
//
//            if (password != confirmPass) {
//                etPassword.error = "Password are not matching."
//                etPassword.requestFocus()
//                return@setOnClickListener
//            }
//            else {
////                val userD = User(firstName = fullName, username = username, email = email, phone = phone, password = password)
//                val user = AccountModel(accFN = fullName, accUN = username, accEmail = email, accPhone = phone, accPwd = password)
//                CoroutineScope(Dispatchers.IO).launch {
////                    UserDB
////                        .getInstance(this@RegisterActivity)
////                        .getUserDAO()
////                        .registerUser(userD)
//                    try {
//                        val userRepository = UserRepository()
//                        val response = userRepository.registerUser(user)
//                        if(response.success == true) {
//                            withContext(Dispatchers.Main) {
//                                Toast.makeText(
//                                    this@RegisterActivity,
//                                    "User Registration Succeeded.",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//
//                                var intent = Intent(this@RegisterActivity,LoginActivity::class.java)
//                                startActivity(intent)
//                                finish()
//                            }
//                        }
//                    } catch (ex: Exception) {
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(
//                                this@RegisterActivity,
//                                ex.toString(), Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                }
//                startActivity(intent)
//            }
//        }
//
//        btnSignIn.setOnClickListener {
//            startActivity(Intent(
//                this@RegisterActivity,
//                LoginActivity::class.java
//            ))
//        }
//    }
//
//    fun binding() {
//        etFullName = findViewById(R.id.etFullName)
//        etUsername = findViewById(R.id.etUsername)
//        etEmail = findViewById(R.id.etEmail)
//        etPhone = findViewById(R.id.etPhone)
//        etPassword = findViewById(R.id.etPassword)
//        etConfirmPass = findViewById(R.id.etConfirmPass)
//        btnRegister = findViewById(R.id.btnRegister)
//        btnSignIn = findViewById(R.id.btnSignIn)
//    }
//}