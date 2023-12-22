package com.quid.io.spring.stream.gateway.web

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/live")
class LiveController {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/{user}")
    fun getLivePage(@PathVariable user: String, model : Model): String {
        log.info("Serving live page for user: $user")
        model.addAttribute("user", user)
        return "live"
    }

}