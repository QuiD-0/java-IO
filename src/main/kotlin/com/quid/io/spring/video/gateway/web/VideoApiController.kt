package com.quid.io.spring.video.gateway.web

import com.quid.io.spring.video.usecase.GetFluxResource
import com.quid.io.spring.video.usecase.GetRangeResource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/video")
class VideoApiController(
    private val rangeResource: GetRangeResource,
    private val fluxResource: GetFluxResource
) {

    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    @GetMapping("{id}", produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun getVideo(@RequestHeader headers: HttpHeaders, @PathVariable id: String): ResourceRegion =
        rangeResource(id, headers.range.firstOrNull())

    @GetMapping("/flux/{id}", produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun getVideos(@RequestHeader headers: HttpHeaders, @PathVariable id: String): Mono<ResourceRegion> =
        fluxResource(id, headers.range.firstOrNull())
}