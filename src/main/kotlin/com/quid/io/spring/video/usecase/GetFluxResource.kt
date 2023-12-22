package com.quid.io.spring.video.usecase

import com.quid.io.spring.video.gateway.repository.VideoPathRepository
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpRange
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

fun interface GetFluxResource {
    operator fun invoke(id: String, range: HttpRange?): Mono<ResourceRegion>

    @Service
    class GetFluxResourceImpl(
        private val resourceLoader: ResourceLoader,
        private val videoPathRepository: VideoPathRepository
    ) : GetFluxResource {

        override fun invoke(id: String, range: HttpRange?): Mono<ResourceRegion> =
            videoPathRepository.byId(id)
                .run { resourceLoader.getResource("file:$this") }
                .let { ResourceRegion(it,  getRange(it, range), CHUNK_SIZE)}
                .let { Mono.fromSupplier { it } }

        private fun getRange(resource: Resource, range: HttpRange?): Long =
            range?.getRangeStart(resource.contentLength()) ?: 0

        companion object {
            const val CHUNK_SIZE: Long = 1024 * 1024
        }
    }
}