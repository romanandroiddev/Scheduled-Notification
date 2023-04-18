package com.example.schedulednotificationapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val notification = NotificationCompat.Builder(context, "Texnopos_7117")
                .setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("Hello world")
                .setAutoCancel(true)
                .setContentText("It is simple description for testing notifications").build()


            val notificationManager =
                it.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        "Texnopos_7117",
                        "Alarm shig'ariw ushin",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
            }
            notificationManager.notify("TTTT", 0, notification)
        }

    }
}