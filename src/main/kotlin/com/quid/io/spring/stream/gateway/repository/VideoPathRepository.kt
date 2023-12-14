package com.quid.io.spring.stream.gateway.repository

import org.springframework.stereotype.Repository

interface VideoPathRepository {

    fun byId(id: String): String

    @Repository
    class VideoPathRepositoryImpl(
        private val mock: VideoPathMockRepository
    ) : VideoPathRepository {

        override fun byId(id: String): String =
            mock.byId(id) ?: throw IllegalArgumentException("Video not found")
    }
}