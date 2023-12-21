package com.quid.io.spring.stream.usecase

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.io.File

fun interface ServeHls {
    operator fun invoke(user: String): Resource

    @Service
    class HlsServeUseCase(
        @Value("\${live.hls-path}")
        private val streamPath: String,
        @Value("\${user.home}")
        private val home: String
    ) : ServeHls {
        init {
            checkDir("$home/$streamPath")
        }

        override fun invoke(user: String): Resource =
            if (user.endsWith(".ts")) {
                FileSystemResource("$home/$streamPath/$user")
            } else {
                FileSystemResource("$home/$streamPath/$user.m3u8")
            }


        private fun checkDir(streamPath: String) {
            val dir = File(streamPath)
            if (!dir.exists()) {
                dir.mkdir()
            }
        }
    }
}