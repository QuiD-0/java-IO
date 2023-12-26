package com.quid.io.spring.media.gateway.repository

import org.springframework.stereotype.Repository

interface PathRepository {

    fun byVideoId(id: String): String
    fun byAudioId(id: String): String

    @Repository
    class PathRepositoryImpl(
        private val mock: PathMockRepository
    ) : PathRepository {

        override fun byVideoId(id: String): String =
            mock.byVideo(id) ?: throw IllegalArgumentException("Video not found")

        override fun byAudioId(id: String): String =
            mock.byAudio(id) ?: throw IllegalArgumentException("Video not found")
    }

}