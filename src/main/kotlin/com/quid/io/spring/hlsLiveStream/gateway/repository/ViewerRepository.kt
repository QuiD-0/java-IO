package com.quid.io.spring.hlsLiveStream.gateway.repository

import com.quid.io.spring.hlsLiveStream.gateway.repository.memory.InMemoryViewerRepository
import org.springframework.stereotype.Repository

interface ViewerRepository {
    fun add(user: String, viewer: String)
    fun list(user: String): List<String>

    @Repository
    class ViewerRepositoryImpl(
        private val inMemoryViewerRepository: InMemoryViewerRepository
    ) : ViewerRepository {
        override fun add(user: String, viewer: String) {
            inMemoryViewerRepository.add(user, viewer)
        }

        override fun list(user: String): List<String> {
            return inMemoryViewerRepository.list(user)
        }
    }

}