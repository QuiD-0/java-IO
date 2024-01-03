package com.quid.io.spring.chat.gateway.web

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/chat")
class ChatAPIController {

    @PostMapping
    fun sendChat() {
        // todo
    }
}