package com.quid.io.spring.media.gateway.web

import com.quid.io.spring.media.usecase.GetRangeResource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/music")
class MusicApiController(
    private val rangeResource: GetRangeResource,
) {

    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    @GetMapping("/{title}", produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun getAudio(@RequestHeader headers: HttpHeaders, @PathVariable title: String): ResourceRegion {
        return rangeResource.audio(title, headers.range.firstOrNull())
    }

}