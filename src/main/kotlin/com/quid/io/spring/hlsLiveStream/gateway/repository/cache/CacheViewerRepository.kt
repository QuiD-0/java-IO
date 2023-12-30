package com.quid.io.spring.hlsLiveStream.gateway.repository.cache

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

interface CacheViewerRepository {

    fun add(user: String, viewer: String)
    fun list(user: String): List<String>

    @Component
    class CacheViewerRepositoryImpl : CacheViewerRepository {

        private val map: MutableMap<String, MutableSet<Viewer>> = ConcurrentHashMap()

        override fun add(user: String, viewer: String) {
            map[user] = map[user]?.apply { add(Viewer(viewer)) } ?: mutableSetOf(Viewer(viewer))
        }

        override fun list(user: String): List<String> = map[user]
            ?.let { map[user]!!.map { it.viewer } }
            ?: emptyList()

        @Scheduled(fixedRate = 10000)
        fun clear() {
            map.map { it.key }.forEach { key ->
                map[key] = map[key]!!.filter { !it.isExpired() }.toMutableSet()
            }
        }

        data class Viewer(
            val viewer: String, val time: LocalDateTime = LocalDateTime.now()
        ) {
            fun isExpired(): Boolean = time.plusSeconds(5).isBefore(LocalDateTime.now())

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Viewer

                return viewer == other.viewer
            }

            override fun hashCode(): Int {
                return viewer.hashCode()
            }
        }
    }
}