package com.quid.io.spring.stream.usecase

import com.quid.io.spring.stream.gateway.repository.VideoPathRepository
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpRange
import org.springframework.stereotype.Service
import kotlin.math.min

fun interface GetVideoResource {
    operator fun invoke(id: String, range: HttpRange?): ResourceRegion

    @Service
    class GetVideoResourceImpl(private val videoPathRepository: VideoPathRepository) : GetVideoResource {

        override fun invoke(id: String, range: HttpRange?): ResourceRegion =
            videoPathRepository.byId(id)
                .run { FileSystemResource(this) }
                .let { videoResource ->
                    val (start, end) = getRange(videoResource, range)
                    ResourceRegion(videoResource, start, end)
                }


        private fun getRange(videoResource: FileSystemResource, range: HttpRange?): Pair<Long, Long> =
            range?.let {
                val start: Long = it.getRangeStart(videoResource.contentLength())
                val end: Long = it.getRangeEnd(videoResource.contentLength())
                Pair(start, min(start + CHUNK_SIZE, end))
            } ?: Pair(0, min(CHUNK_SIZE, videoResource.contentLength()))

        companion object{
            const val CHUNK_SIZE: Long = 1000000L
        }
    }
}