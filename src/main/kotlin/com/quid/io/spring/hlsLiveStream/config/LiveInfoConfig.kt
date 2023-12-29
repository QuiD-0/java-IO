package com.quid.io.spring.hlsLiveStream.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class LiveInfoConfig {

    @Value("\${live.hls-path}")
    private lateinit var path: String

    @Value("\${live.radio-path}")
    private lateinit var radioPath: String

    @Bean
    fun streamInfo(): StreamInfo = StreamInfo(path, radioPath)


    class StreamInfo(
        val path: String,
        val radioPath: String
    ){
        private val log = LoggerFactory.getLogger(this::class.java)

        init {
            checkDir(path)
        }

        private fun checkDir(streamPath: String) {
            val dir = File(streamPath)
            if (!dir.exists()) {
                dir.mkdir()
            }
        }
    }
}