package com.quid.io.spring.stream.usecase.live

import com.quid.io.spring.stream.config.LiveInfoConfig.StreamInfo
import org.springframework.stereotype.Service
import java.io.File

fun interface CheckLive {
    operator fun invoke(user: String): Boolean

    @Service
    class CheckLiveUseCase(
        private val streamInfo: StreamInfo,
    ) : CheckLive {
        override fun invoke(user: String): Boolean =
            File(streamInfo.toM3u8Path(user)).exists()
    }
}