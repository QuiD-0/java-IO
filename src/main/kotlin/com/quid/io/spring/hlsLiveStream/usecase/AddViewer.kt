package com.quid.io.spring.hlsLiveStream.usecase

import com.quid.io.spring.hlsLiveStream.gateway.repository.ViewerRepository
import org.springframework.stereotype.Service

fun interface AddViewer {

    operator fun invoke(user: String, viewer: String)

    @Service
    class AddViewerUseCase(
         private val viewerRepository: ViewerRepository
    ) : AddViewer {

        override fun invoke(user: String, viewer: String) {
            filterUser(user)
                .let { viewerRepository.add(it, viewer) }
        }

        private fun filterUser(user: String) =
            takeIf { user.endsWith(".ts") }
                ?.let { user.split("-") }?.get(0)
                ?: user
    }
}