package com.quid.io.spring.hlsLiveStream.gateway.web

import com.quid.io.spring.hlsLiveStream.usecase.AddViewer
import com.quid.io.spring.hlsLiveStream.usecase.FindChannels
import com.quid.io.spring.hlsLiveStream.usecase.FindViewers
import com.quid.io.spring.hlsLiveStream.usecase.ServeHls
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/live")
class StreamingApiController(
    private val serveHls: ServeHls,
    private val channels: FindChannels,
    private val addViewer: AddViewer,
    private val viewers: FindViewers
) {

    @GetMapping("/{user}")
    fun getLive(@PathVariable user: String, request: HttpServletRequest): Resource =
        serveHls.live(user)
            .also { addViewer(user, request.session.id) }

    @GetMapping("/check/{user}")
    fun checkLiveChannel(@PathVariable user: String): Boolean =
        channels.live().contains(user)

    @GetMapping("/list")
    fun listLiveChannels(): List<String> = channels.live()

    @GetMapping("/list/{user}")
    fun listLiveViewers(@PathVariable user: String): List<String> = viewers(user)

    @GetMapping("/list/{user}/count")
    fun countLiveViewers(@PathVariable user: String): Int = viewers(user).size

}