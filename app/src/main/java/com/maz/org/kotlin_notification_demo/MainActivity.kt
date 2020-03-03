package com.maz.org.kotlin_notification_demo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID: String = "com.maz.org"
    private val SIMPLE_NOTIFICATION_ID: Int = 1
    private val EXTENDED_NOTIFICATION_ID: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        initSimpleNotification()
        initExtendedNotification()
    }

    private fun initSimpleNotification() {
        var simpleNotificationButton: Button = findViewById(R.id.button_simple_notification)
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("My Custom Simple-Notification")
            .setContentText("This is a Custom Simple-Notification!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        simpleNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(SIMPLE_NOTIFICATION_ID, builder.build())
            }
        }
    }

    private fun initExtendedNotification() {
        var simpleNotificationButton: Button = findViewById(R.id.button_extended_notification)
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("My Custom Extended-Notification")
            .setContentText("This is a Custom Extended-Notification with much more text displayed!\nThis is a Custom Extended-Notification with much more text displayed!\nThis is a Custom Extended-Notification with much more text displayed!\nThis is a Custom Extended-Notification with much more text displayed!\nThis is a Custom Extended-Notification with much more text displayed!")
            .setStyle(NotificationCompat.BigTextStyle().bigText("This is a Custom Extended-Notification with much more text displayed!\nThis is a Custom Extended-Notification with much more text displayed!\nThis is a Custom Extended-Notification with much more text displayed!\nThis is a Custom Extended-Notification with much more text displayed!\nThis is a Custom Extended-Notification with much more text displayed!"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        simpleNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(EXTENDED_NOTIFICATION_ID, builder.build())
            }
        }
    }

    /**
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Custom Channel"
            val descriptionText = "This is my Custom Channel for Notifications!"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
