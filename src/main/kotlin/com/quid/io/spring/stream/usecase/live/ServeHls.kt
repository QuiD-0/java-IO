package com.quid.io.spring.stream.usecase.live

import com.quid.io.spring.stream.config.LiveInfoConfig.StreamInfo
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

fun interface ServeHls {
    operator fun invoke(user: String): Resource

    @Service
    class HlsServeUseCase(
        private val streamInfo: StreamInfo,
    ) : ServeHls {

        override fun invoke(user: String): Resource =
            if (user.endsWith(".ts")) {
                FileSystemResource(streamInfo.toTsPath(user))
            } else {
                FileSystemResource(streamInfo.toM3u8Path(user))
            }
    }
}