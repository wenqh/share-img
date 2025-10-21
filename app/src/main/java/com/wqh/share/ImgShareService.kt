package com.wqh.share

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast

class ImgShareService : Service() {

    override fun onCreate() {
        super.onCreate()

        Thread {
            HttpServer(8080).start()
        }.start()

        // 2分钟后自动退出
        Handler(Looper.getMainLooper()).postDelayed({
            stopSelf()
            Toast.makeText(applicationContext, "服务已关闭", Toast.LENGTH_SHORT).show()
        }, 2 * 60 * 1000)
    }

    override fun onBind(intent: Intent?): IBinder? = null

}
