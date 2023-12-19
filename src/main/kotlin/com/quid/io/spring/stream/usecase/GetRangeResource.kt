package com.quid.io.spring.stream.usecase

import com.quid.io.spring.stream.gateway.repository.VideoPathRepository
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpRange
import org.springframework.stereotype.Service
import kotlin.math.min

fun interface GetRangeResource {
    operator fun invoke(id: String, range: HttpRange?): ResourceRegion

    @Service
    class GetRangeResourceImpl(private val videoPathRepository: VideoPathRepository) : GetRangeResource {

        override fun invoke(id: String, range: HttpRange?): ResourceRegion =
            videoPathRepository.byId(id)
                .run { FileSystemResource(this) }
                .let { ResourceRegion(it,  getRange(it, range), CHUNK_SIZE) }


        private fun getRange(videoResource: FileSystemResource, range: HttpRange?): Long =
            range?.getRangeStart(videoResource.contentLength()) ?: 0

        companion object{
            const val CHUNK_SIZE: Long = 1024 * 1024
        }
    }
}