package com.khadga.meromedapp.notification

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.khadga.meromedapp.R

class NotificationSender(val context: Context, val title:String, val body:String) {
    val notificationManager = NotificationManagerCompat.from(context)
    var notificationChannels = NotificationChannels(context)
    @RequiresApi(Build.VERSION_CODES.O)

    fun createHighPriority()
    {
        notificationChannels.createNotificationChannels()
        val notification = NotificationCompat.Builder(context,notificationChannels.channel1)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentTitle(title)
            .setContentText(body)
            .setColor(Color.BLUE)
            .build()
        notificationManager.notify(1,notification)
    }
    @RequiresApi(Build.VERSION_CODES.O)

    fun createLowPriority()
    {
        notificationChannels.createNotificationChannels()
        val notification = NotificationCompat.Builder(context,notificationChannels.channel2)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentTitle(title)
            .setContentText(body)
            .setColor(Color.BLUE)
            .build()
        notificationManager.notify(2,notification)
    }
}