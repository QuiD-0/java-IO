package com.quid.io.spring.stream.gateway.repository

import org.springframework.stereotype.Repository

@Repository
class VideoPathMockRepository {

    fun byId(id: String): String? {
        return "src/main/resources/asset/sample.mp4"
    }
}