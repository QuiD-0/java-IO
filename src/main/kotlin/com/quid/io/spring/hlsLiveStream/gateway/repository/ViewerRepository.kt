package com.quid.io.spring.hlsLiveStream.gateway.repository

import com.quid.io.spring.hlsLiveStream.gateway.repository.cache.CacheViewerRepository
import org.springframework.stereotype.Repository

interface ViewerRepository {
    fun add(user: String, viewer: String)
    fun list(user: String): List<String>

    @Repository
    class ViewerRepositoryImpl(
        private val cacheViewerRepository: CacheViewerRepository
    ) : ViewerRepository {
        override fun add(user: String, viewer: String) {
            cacheViewerRepository.add(user, viewer)
        }

        override fun list(user: String): List<String> {
            return cacheViewerRepository.list(user)
        }
    }

}