package com.maz.org.kotlin_notification_demo

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * @author Maz47
 * created 03.03.2020
 */
class MainActivity : AppCompatActivity() {

    private val _defaultChannelID: String = "DEFAULT_NOTIFICATION_CHANNEL"
    private val _importantChannelID: String = "IMPORTANT_NOTIFICATION_CHANNEL"

    private val _simpleNotificationID: Int = 1
    private val _bigTextNotificationID: Int = 2
    private val _bigImageNotificationID: Int = 3
    private val _inboxNotificationID: Int = 4
    private val _conversationNotificationID: Int = 5
    private val _lockScreenPublicNotificationID: Int = 6
    private val _lockScreenSecretNotificationID: Int = 7
    private val _lockScreenPrivateNotificationID: Int = 8
    private val _progressBarNotificationID: Int = 9
    private val _intentNotificationID: Int = 10
    private val _notificationWithIntentBackStackID: Int = 11
    private val _notificationWithFullScreenIntentID: Int = 12
    private val _notificationWithActionsID: Int = 13
    private val _notificationWithMediaControlsID: Int = 14
    private val _replyNotificationID: Int = 15
    private val _timeSensitiveNotificationID: Int = 16
    private val _groupNotificationID: Int = 17
    private val _customNotificationID: Int = 18

    private val _snoozeAction: String = "ACTION_SNOOZE"

    /**
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDefaultNotificationChannel()
        createImportantNotificationChannel()
        //createNotificationChannelGroup()

        initSimpleNotification()
        initBigTextNotification()
        initBigImageNotification()
        initInboxNotification()
        //initConversationNotification()
        //initLockScreenPublicNotification()
        //initLockScreenSecretNotification()
        //initLockScreenPrivateNotification()
        initNotificationWithProgressBar()
        initNotificationWithIntent()
        //initNotificationWithIntentBackStack()
        //initNotificationWithFullScreenIntent()
        initNotificationWithActions()
        //initNotificationWithMediaControls()
        //initReplyNotification()
        //initTimeSensitiveNotification()
        //initGroupNotification()
        //initCustomNotification()
    }

    /**
     * The simplest notification consists of an icon, a text and a one line content text (description).
     * The notification can be dismissed by swiping. By clicking on the notification nothing will be
     * happen.
     */
    private fun initSimpleNotification() {
        var simpleNotificationButton: Button = findViewById(R.id.button_simple_notification)
        var builder = NotificationCompat.Builder(this, _importantChannelID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.simple_notification_title))
            .setContentText(getString(R.string.simple_notification_content_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        simpleNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(_simpleNotificationID, builder.build())
            }
        }
    }

    /**
     * If the notification should provide multi-line content text, the specific style (BigTextStyle) 
     * has to be applied.
     */
    private fun initBigTextNotification() {
        var bigTextNotificationButton: Button = findViewById(R.id.button_big_text_notification)
        var builder = NotificationCompat.Builder(this, _importantChannelID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.big_text_notification_title))
            .setStyle(NotificationCompat.BigTextStyle().bigText(getString(R.string.big_text_notification_content_text)))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        bigTextNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(_bigTextNotificationID, builder.build())
            }
        }
    }

    /**
     * If the notification should provide an Image, the specific style (BigTextStyle)
     * has to be applied.
     */
    private fun initBigImageNotification() {
        var bigImageNotificationButton: Button = findViewById(R.id.button_big_image_notification)
        val myBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.android)
        var builder = NotificationCompat.Builder(this, _importantChannelID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.big_image_notification_title))
            .setContentText(getString(R.string.big_image_notification_content_text))
            .setLargeIcon(myBitmap)
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(myBitmap)
                .bigLargeIcon(null))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        bigImageNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(_bigImageNotificationID, builder.build())
            }
        }
    }

    /**
     */
    private fun initInboxNotification() {
        var inboxNotificationButton: Button = findViewById(R.id.button_inbox_notification)
        var builder = NotificationCompat.Builder(this, _importantChannelID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.inbox_notification_title))
            .setContentText(getString(R.string.inbox_notification_content_text))
            .setStyle(NotificationCompat.InboxStyle()
                .addLine(getString(R.string.inbox_notification_line_1))
                .addLine(getString(R.string.inbox_notification_line_2))
                .addLine(getString(R.string.inbox_notification_line_3)))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        inboxNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(_inboxNotificationID, builder.build())
            }
        }
    }

    /**
     */
    private fun initNotificationWithProgressBar() {
        var progressBarNotificationButton: Button = findViewById(R.id.button_progress_bar_notification)
        var builder = NotificationCompat.Builder(this, _importantChannelID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.progress_bar_notification_title))
            .setContentText(getString(R.string.progress_bar_notification_context_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        progressBarNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                builder.setProgress(100, 0, false)
                notify(_progressBarNotificationID, builder.build())
                Thread(Runnable {
                    var counter: Int = 0
                    while (counter < 100) {
                        counter += 20
                        Thread.sleep(2000)
                        builder.setProgress(100, counter, false)
                            .setContentText(getString(R.string.progress_bar_notification_context_text) + " " + counter.toString() + "%")
                        notify(_progressBarNotificationID, builder.build())
                    }
                    builder.setContentText(getString(R.string.progress_bar_notification_context_text_complete)).setProgress(0, 0, false)
                    notify(_progressBarNotificationID, builder.build())
                }).start()
            }
        }
    }

    /**
     * If the notification should react to a user click and start an activity, a pending intent has
     * to be provided!
     *
     * ATTENTION: You may define the AUTO_CANCEL attribute to true so the notification will be
     *            removed when the user clicks it!
     */
    private fun initNotificationWithIntent() {
        var intentNotificationButton: Button = findViewById(R.id.button_intent_notification)
        val intent = Intent(this, NotificationDetail::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        var builder = NotificationCompat.Builder(this, _importantChannelID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.intent_notification_title))
            .setContentText(getString(R.string.intent_notification_context_text))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        intentNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(_intentNotificationID, builder.build())
            }
        }
    }

    /**
     */
    private fun initNotificationWithActions() {
        var actionsNotificationButton: Button = findViewById(R.id.button_actions_notification)
        val snoozeIntent = Intent(this, MyBroadcastReceiver::class.java).apply {
            action = _snoozeAction
            putExtra(EXTRA_NOTIFICATION_ID, 0)
        }
        val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)
        var builder = NotificationCompat.Builder(this, _importantChannelID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.actions_notification_title))
            .setContentText(getString(R.string.actions_notification_context_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_snooze, getString(R.string.action_snooze), snoozePendingIntent)

        actionsNotificationButton.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(_actionsNotificationID, builder.build())
            }
        }
    }

    /**
     * Creates a Notification-Channel needed for generating Notification in Android 8.0 (O) and above!
     * This channel describes the behavior of the notifications published in this channel. The User
     * can modify the behavior inside the app settings. Every created Channel will be visible and has
     * his own settings. So you can split notifications into several channels. For example Facebook
     * has different channels for tags and comments.
     *
     * The name and description will be visible in the app settings panel.
     *
     * Notifications can be published to a specific channel by referencing the channel over the
     * CHANNEL_ID!
     *
     * Properties like led-color, sound, vibration-pattern, etc. can be defined per channel.
     * If you want to control the same notification (e.g. comments) for multiple different use cases
     * (e.g. work account and personal account) inside your app, you can create channel groups.
     *
     * ATTENTION: Once a channel has been created, you cannot modify any properties! Only the user
     *            can configure it by changing the settings. If you want to read the current settings
     *            you can read the changed by calling getNotificationChannel() or getNotificationChannels().
     */
    private fun createDefaultNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(_defaultChannelID, getString(R.string.default_channel_name), importance).apply {
                description = getString(R.string.default_channel_description)
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun createImportantNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(_importantChannelID, getString(R.string.important_channel_name), importance).apply {
                description = getString(R.string.important_channel_description)
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
