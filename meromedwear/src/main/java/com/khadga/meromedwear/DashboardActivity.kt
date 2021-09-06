package com.khadga.meromedwear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.TextView
import com.khadga.meromedapp.api.ServiceBuilder

class DashboardActivity : WearableActivity() {
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvPhone: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvUsername = findViewById(R.id.tvUsername)
        tvPhone = findViewById(R.id.tvPhone)

        tvName.text = ServiceBuilder.user?.accFN
        tvUsername.text = ServiceBuilder.user?.accUN
        tvEmail.text = ServiceBuilder.user?.accEmail
        tvPhone.text = ServiceBuilder.user?.accPhone
        // Enables Always-on
        setAmbientEnabled()
    }
}