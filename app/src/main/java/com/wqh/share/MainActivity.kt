package com.wqh.share

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permission = Manifest.permission.READ_MEDIA_IMAGES
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), 100)
            return
        }

        startService(Intent(this, ShareImgService::class.java))

        val ip = getLocalLanIp() ?: "IP获取失败"
        Toast.makeText(applicationContext, "服务已启动：$ip:8080", Toast.LENGTH_LONG).show()

        finish()
    }
}
