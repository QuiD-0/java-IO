package com.quid.io.spring.stream.usecase

import com.quid.io.spring.stream.gateway.repository.VideoPathRepository
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpRange
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import kotlin.math.min

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
                .let { videoResource ->
                    val (start, end) = getRange(videoResource, range)
                    ResourceRegion(videoResource, start, end)
                }
                .let { Mono.fromSupplier { it } }

        private fun getRange(videoResource: Resource, range: HttpRange?): Pair<Long, Long> =
            range?.let {
                val start: Long = it.getRangeStart(videoResource.contentLength())
                val end: Long = it.getRangeEnd(videoResource.contentLength())
                Pair(start, min(start + CHUNK_SIZE, end))
            } ?: Pair(0, min(CHUNK_SIZE, videoResource.contentLength()))

        companion object {
            const val CHUNK_SIZE: Long = 10 * 1024 * 1024
        }
    }
}