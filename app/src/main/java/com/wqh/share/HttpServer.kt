package com.wqh.share

import fi.iki.elonen.NanoHTTPD
import java.io.FileInputStream

class HttpServer(port: Int) : NanoHTTPD(port) {
    override fun serve(session: IHTTPSession): Response {
        return when (session.uri) {
            "/index" -> newFixedLengthResponse(
                "<html><body><img src=\"/image\" width=\"100%\" /></body></html>"
            )
            "/" -> {
                getLatestScreenshot()?.let {
                    newChunkedResponse(Response.Status.OK,
                        if (it.name.endsWith(".png")) "image/png" else "image/jpeg",
                        FileInputStream(it)
                    )
                } ?: newFixedLengthResponse("找不到截图")
            }
            else -> newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "404 Not Found")
        }
    }
}
