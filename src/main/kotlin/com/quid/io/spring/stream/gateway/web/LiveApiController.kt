package com.quid.io.spring.stream.gateway.web

import com.quid.io.spring.stream.usecase.ServeHls
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/live")
class LiveApiController(
    private val serveHls: ServeHls,
) {

    @GetMapping("/{user}")
    fun getLive(@PathVariable user: String): Resource =
        serveHls(user)

}