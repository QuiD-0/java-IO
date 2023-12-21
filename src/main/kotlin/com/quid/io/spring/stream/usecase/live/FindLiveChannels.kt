package com.quid.io.spring.stream.usecase.live

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

fun interface FindLiveChannels {
    operator fun invoke(): List<String>

    @Service
    class FindLiveChannelsUseCase(
        @Value("\${live.hls-path}")
        private val streamPath: String,
        @Value("\${user.home}")
        private val home: String
    ) : FindLiveChannels {
        override fun invoke(): List<String> = File("$home/$streamPath").listFiles()
            ?.filter { it.name.endsWith(".m3u8") }
            ?.map { it.name.removeSuffix(".m3u8") }
            ?: emptyList()
    }
}