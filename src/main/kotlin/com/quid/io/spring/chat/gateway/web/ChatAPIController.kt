package com.quid.io.spring.chat.gateway.web

import com.quid.io.spring.chat.SendChatUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chat")
class ChatAPIController(
    private val pubChatting: SendChatUseCase
) {

    @PostMapping
    fun sendChat() {
        pubChatting()
    }
}