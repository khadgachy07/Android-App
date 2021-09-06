package com.khadga.meromedwear

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.khadga.meromedapp.api.ServiceBuilder
import com.khadga.meromedapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : WearableActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogIn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogIn = findViewById(R.id.btnLogin)

        btnLogIn.setOnClickListener {
            login()
        }
        // Enables Always-on
        setAmbientEnabled()
    }

    private fun login() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val ur = UserRepository()
                val response = ur.loginUser(username, password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer " + response.token
                    ServiceBuilder.user = response.data
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            response.data.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@LoginActivity, "Welcome $username", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Invalid Cerdentials",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Invalid $ex", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}