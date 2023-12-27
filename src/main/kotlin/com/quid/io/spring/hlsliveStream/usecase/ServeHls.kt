package com.quid.io.spring.hlsliveStream.usecase

import com.quid.io.spring.hlsliveStream.config.LiveInfoConfig.StreamInfo
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

interface ServeHls {
    fun live(user: String): Resource
    fun radio(user: String): Resource
    fun getHlsResourceFromPath(user: String, hlsPath: String): Resource =
        if (user.endsWith(".ts")) {
            FileSystemResource("$hlsPath/$user")
        } else {
            FileSystemResource("$hlsPath/$user.m3u8")
        }

    @Service
    class HlsServeUseCase(
        private val streamInfo: StreamInfo,
    ) : ServeHls {

        override fun live(user: String): Resource = getHlsResourceFromPath(user, streamInfo.path)

        override fun radio(user: String): Resource = getHlsResourceFromPath(user, streamInfo.radioPath)

    }
}