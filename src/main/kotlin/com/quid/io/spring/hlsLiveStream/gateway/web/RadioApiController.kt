package com.quid.io.spring.hlsLiveStream.gateway.web

import com.quid.io.spring.hlsLiveStream.usecase.FindChannels
import com.quid.io.spring.hlsLiveStream.usecase.ServeHls
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/radio")
class RadioApiController(
    private val serveHls: ServeHls,
    private val channels: FindChannels
) {

    @GetMapping("/{user}")
    fun getRadio(@PathVariable user: String): Resource =
        serveHls.radio(user)

    @GetMapping("/list")
    fun listRadioChannels(): List<String> = channels.radio()

    @GetMapping("/check/{user}")
    fun checkRadioChannel(@PathVariable user: String): Boolean = channels.radio().contains(user)

}