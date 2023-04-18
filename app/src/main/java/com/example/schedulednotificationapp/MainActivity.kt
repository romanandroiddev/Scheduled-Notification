package com.example.schedulednotificationapp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.schedulednotificationapp.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import us.romanandroiddev.scheduled_notification_builder.ScheduledNotification
import us.romanandroiddev.scheduled_notification_builder.data.models.NotificationType
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnShowNotification.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            val pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_IMMUTABLE
            )
            val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setAlarmClock(AlarmManager.AlarmClockInfo(System.currentTimeMillis()+10000,pendingIntent),pendingIntent)

        }

        binding.btnScheduledNotification.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder().build()


            timePicker.show(supportFragmentManager, "TTTT")
            timePicker.addOnPositiveButtonClickListener {

                val calendar = Calendar.getInstance()
                calendar.set(Calendar.MILLISECOND, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MINUTE, timePicker.minute)
                calendar.set(Calendar.HOUR, timePicker.hour)

                ScheduledNotification.Builder(this).setType(NotificationType.SCHEDULED)
                    .setTitle("Scheduled notification title")
                    .setDescription("It is description for scheduled notification")
                    .setIcon(R.drawable.ic_launcher_background)
                    .setScheduledTime(calendar.timeInMillis).build()
                Toast.makeText(this, "SUCCESFULLY ADDED TO QUEUE", Toast.LENGTH_SHORT).show()
            }
        }

    }
}