package com.quid.io.spring.hlsliveStream.usecase

import com.quid.io.spring.hlsliveStream.config.LiveInfoConfig.StreamInfo
import org.springframework.stereotype.Service
import java.io.File

fun interface FindLiveChannels {
    operator fun invoke(): List<String>

    @Service
    class FindLiveChannelsUseCase(
        private val streamInfo: StreamInfo,
    ) : FindLiveChannels {
        override fun invoke(): List<String> = File(streamInfo.path).listFiles()
            ?.map { it.name }
            ?: emptyList()
    }
}