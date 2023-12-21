package com.quid.io.spring.stream.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class LiveInfoConfig {

    @Value("\${live.hls-path}")
    private lateinit var path: String

    @Value("\${user.home}")
    private lateinit var home: String

    @Bean
    fun streamInfo(): StreamInfo = StreamInfo(path, home)


    class StreamInfo(
        val path: String,
        val home: String,
    ){
        init {
            checkDir("$home/$path")
        }

        fun toStreamPath(): String = "$home/$path"
        fun toTsPath(user: String): String = "$home/$path/$user"
        fun toM3u8Path(user: String): String = "$home/$path/$user.m3u8"

        private fun checkDir(streamPath: String) {
            val dir = File(streamPath)
            if (!dir.exists()) {
                dir.mkdir()
            }
        }
    }
}