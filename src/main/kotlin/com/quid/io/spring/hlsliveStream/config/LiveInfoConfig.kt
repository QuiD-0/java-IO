package com.quid.io.spring.hlsliveStream.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class LiveInfoConfig {

    @Value("\${live.hls-path}")
    private lateinit var path: String

    @Bean
    fun streamInfo(): StreamInfo = StreamInfo(path)


    class StreamInfo(
        val path: String,
    ){
        private val log = LoggerFactory.getLogger(this::class.java)

        init {
            checkDir(path)
        }

        fun toTsPath(user: String, tsSeq: String): String = "$path/$user/$user-$tsSeq"
        fun toM3u8Path(user: String): String = "$path/$user/index.m3u8"

        private fun checkDir(streamPath: String) {
            val dir = File(streamPath)
                .also { log.info("Checking stream path: $it") }
            if (!dir.exists()) {
                dir.mkdir()
            }
        }
    }
}