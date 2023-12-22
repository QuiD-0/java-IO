package com.quid.io.spring.stream.config

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
        val filePath: String
            get() = "file://$path"


        private val log = LoggerFactory.getLogger(this::class.java)

        init {
            checkDir(path)
        }

        fun toTsPath(user: String): String = "file://$path/$user"
        fun toM3u8Path(user: String): String = " file://$path/$user.m3u8"

        private fun checkDir(streamPath: String) {
            val dir = File(streamPath)
                .also { log.info("Checking stream path: $it") }
            if (!dir.exists()) {
                dir.mkdir()
            }
        }
    }
}