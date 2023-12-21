package com.quid.io.spring.stream.gateway.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/live")
class LiveController {

    @GetMapping("/{user}")
    fun getLivePage(@PathVariable user: String, model : Model): String {
        model.addAttribute("user", user)
        return "live"
    }

}