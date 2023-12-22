package com.quid.io.spring.stream.usecase.live

import com.quid.io.spring.stream.config.LiveInfoConfig.StreamInfo
import org.springframework.stereotype.Service
import java.io.File

fun interface FindLiveChannels {
    operator fun invoke(): List<String>

    @Service
    class FindLiveChannelsUseCase(
        private val streamInfo: StreamInfo,
    ) : FindLiveChannels {
        override fun invoke(): List<String> = File(streamInfo.path).listFiles()
            ?.filter { it.name.endsWith(".m3u8") }
            ?.map { it.name.removeSuffix(".m3u8") }
            ?: emptyList()
    }
}