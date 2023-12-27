package com.quid.io.spring.hlsLiveStream.gateway.web

import com.quid.io.spring.hlsLiveStream.usecase.FindChannels
import com.quid.io.spring.hlsLiveStream.usecase.ServeHls
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/live")
class StreamingApiController(
    private val serveHls: ServeHls,
    private val channels: FindChannels
) {

    @GetMapping("/{user}")
    fun getLive(@PathVariable user: String): Resource =
        serveHls.live(user)

    @GetMapping("/check/{user}")
    fun checkLiveChannel(@PathVariable user: String): Boolean =
        channels.live().contains(user)

    @GetMapping("/list")
    fun listLiveChannels(): List<String> = channels.live()


}