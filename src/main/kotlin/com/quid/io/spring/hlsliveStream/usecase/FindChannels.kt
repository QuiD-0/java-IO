package com.quid.io.spring.hlsliveStream.usecase

import com.quid.io.spring.hlsliveStream.config.LiveInfoConfig.StreamInfo
import org.springframework.stereotype.Service
import java.io.File

interface FindChannels {
    fun live(): List<String>
    fun radio(): List<String>

    @Service
    class FindOnAirChannelsUseCase(
        private val streamInfo: StreamInfo,
    ) : FindChannels {
        override fun live(): List<String> = getChannelsFromPath(streamInfo.path)
        override fun radio(): List<String> = getChannelsFromPath(streamInfo.radioPath)


        private fun getChannelsFromPath(path: String) = (File(path).listFiles()
            ?.filter { it.name.endsWith(".m3u8") }
            ?.map { it.name.removeSuffix(".m3u8") }
            ?: emptyList())
    }
}