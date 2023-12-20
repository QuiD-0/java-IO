package com.quid.io.spring.stream.gateway.web

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/live")
class LiveApiController {

    @GetMapping("/{user}")
    fun getLive(@PathVariable user: String): Resource =
        if (user.contains("ts")) {
            val dir = user.split("@")[0]
            FileSystemResource("src/main/resources/asset/hls/$dir/$user")
        } else {
            FileSystemResource("src/main/resources/asset/hls/$user/$user.m3u8")
        }
}