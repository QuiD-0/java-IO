package com.quid.io.spring.stream.usecase.live

import com.quid.io.spring.stream.config.LiveInfoConfig.StreamInfo
import org.slf4j.LoggerFactory
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.io.File

fun interface ServeHls {
    operator fun invoke(user: String): Resource

    @Service
    class HlsServeUseCase(
        private val streamInfo: StreamInfo,
    ) : ServeHls {
        private val log = LoggerFactory.getLogger(this::class.java)

        override fun invoke(user: String): Resource =
            if (user.endsWith(".ts")) {
                FileSystemResource(streamInfo.toTsPath(user))
            } else {
                log.info("Serving HLS for user: $user")
                File(streamInfo.toTsPath(user))
                    .run { FileSystemResource(this.absoluteFile) }
            }
    }
}