package com.quid.io.spring.stream.gateway.web

import com.quid.io.spring.stream.usecase.GetVideoResource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_OCTET_STREAM
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/video")
class VideoApiController(
        private val videoResource: GetVideoResource
) {

    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    @GetMapping("{id}", produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun getVideo(@RequestHeader headers: HttpHeaders, @PathVariable id: String): ResourceRegion =
        videoResource(id, headers.range.firstOrNull())
}