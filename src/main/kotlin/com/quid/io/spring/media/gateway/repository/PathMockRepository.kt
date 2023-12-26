package com.quid.io.spring.media.gateway.repository

import org.springframework.stereotype.Repository

@Repository
class PathMockRepository {

    fun byVideo(id: String): String? {
        return "src/main/resources/asset/sample.mp4"
    }

    fun byAudio(id: String): String? {
        return "src/main/resources/asset/sample.mp3"
    }
}