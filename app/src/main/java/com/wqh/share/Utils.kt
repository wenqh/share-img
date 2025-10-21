package com.wqh.share

import android.os.Environment
import java.io.File
import java.net.Inet4Address
import java.net.NetworkInterface

fun getLocalLanIp(): String? =
    NetworkInterface.getNetworkInterfaces().asSequence()
        .flatMap { it.inetAddresses.asSequence() }
        .filterIsInstance<Inet4Address>()
        .firstOrNull {
            !it.isLoopbackAddress && (
                it.hostAddress.startsWith("192.") ||
                it.hostAddress.startsWith("10.") ||
                it.hostAddress.startsWith("172.")
            )
        }?.hostAddress

fun getLatestScreenshot(): File? {
    return File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Screenshots")
//        Environment.getExternalStorageDirectory(), "ScreenShot")
        .listFiles { it -> it.isFile && !it.isHidden && (it.name.endsWith(".png", true) || it.name.endsWith(".jpg", true)) }
        ?.maxByOrNull { it.lastModified() }
}
