package com.quid.io.spring.stream.usecase.live

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

fun interface CheckLive {
    operator fun invoke(user: String): Boolean

    @Service
    class CheckLiveUseCase(
        @Value("\${live.hls-path}")
        private val streamPath: String,
        @Value("\${user.home}")
        private val home: String
    ) : CheckLive {
        override fun invoke(user: String): Boolean =
            File("$home/$streamPath/$user.m3u8").exists()
    }
}