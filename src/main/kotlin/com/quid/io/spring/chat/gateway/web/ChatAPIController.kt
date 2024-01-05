package com.quid.io.spring.chat.gateway.web

import com.quid.io.spring.chat.usecase.SendChatUseCase
import com.quid.io.spring.chat.domain.Chat
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatAPIController(
    private val pubChatting: SendChatUseCase
) {

    @MessageMapping("/chat")
    fun sendChat(chat: Chat) {
        pubChatting(chat)
    }
}