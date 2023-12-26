package com.quid.io.spring.media.usecase

import com.quid.io.spring.media.gateway.repository.PathRepository
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpRange
import org.springframework.stereotype.Service

interface GetRangeResource {
    fun video(path: String, range: HttpRange?): ResourceRegion
    fun audio(path: String, range: HttpRange?): ResourceRegion

    @Service
    class GetRangeResourceImpl(private val pathRepository: PathRepository) : GetRangeResource {

        override fun video(path: String, range: HttpRange?): ResourceRegion =
            pathRepository.byVideoId(path)
                .run { FileSystemResource(this) }
                .let { ResourceRegion(it, getRange(it, range), CHUNK_SIZE) }

        override fun audio(path: String, range: HttpRange?): ResourceRegion {
            return pathRepository.byAudioId(path)
                .run { FileSystemResource(this) }
                .let { ResourceRegion(it, getRange(it, range), CHUNK_SIZE) }
        }

        private fun getRange(videoResource: FileSystemResource, range: HttpRange?): Long =
            range?.getRangeStart(videoResource.contentLength()) ?: 0

        companion object {
            const val CHUNK_SIZE: Long = 1024 * 1024
        }
    }
}