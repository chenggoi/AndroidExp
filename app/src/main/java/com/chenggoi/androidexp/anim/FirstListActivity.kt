package com.chenggoi.androidexp.anim

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.chenggoi.androidexp.R
import com.chenggoi.androidexp.list.ARecyclerAdapter
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_list.*

/**
 *
 * @author chenggoi
 * @since  2020/8/31
 */
class FirstListActivity : AppCompatActivity() {

    var num = 0
    var canNum = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val adapter = ARecyclerAdapter()
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val bannerChannel = NotificationChannel(
            "a",
            "a",
            NotificationManager.IMPORTANCE_HIGH
        )
        bannerChannel.vibrationPattern = null
        bannerChannel.setSound(null, null)

        manager.createNotificationChannels(arrayListOf(bannerChannel))

        btn.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val list = manager.activeNotifications
            Log.e("CG", "onCreate 39 : ${list.size}")

            val n = NotificationCompat.Builder(this, "a")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("" + num)
                .setContentTitle("" + num)
                .build()
            manager.notify(num, n)
            num++
        }

        btnCancel.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val list = manager.activeNotifications

//            manager.cancelAll()
            for (i in list.size - 1 downTo 0) {
                var sbn = list[i]
                var id = sbn.id
                var n = sbn.notification
                var n2 = NotificationCompat.Builder(this, n).build()

                Log.e("CG", "onCreate 71 : $id $n2")
                manager.notify(id, n)

                Thread.sleep(100)
            }
//            manager.cancel(canNum)
//            canNum++
        }
    }
}