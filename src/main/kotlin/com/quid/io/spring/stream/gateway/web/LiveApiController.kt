package com.quid.io.spring.stream.gateway.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/live")
class LiveApiController {

    @GetMapping("/{user}")
    fun getLive(@PathVariable user: String): String = "Hello $user"

}