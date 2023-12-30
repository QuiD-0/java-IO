package com.quid.io.spring.hlsLiveStream.usecase

import com.quid.io.spring.hlsLiveStream.gateway.repository.ViewerRepository
import org.springframework.stereotype.Service

interface FindViewers {

    operator fun invoke(user: String): List<String>

    @Service
    class FindViewersUseCase(
        private val viewerRepository: ViewerRepository
    ) : FindViewers {

        override fun invoke(user: String): List<String> {
            return viewerRepository.list(user)
        }
    }
}