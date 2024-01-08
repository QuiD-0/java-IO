package com.quid.io.spring.chat.gateway.web

import com.quid.io.spring.chat.usecase.SendChatUseCase
import com.quid.io.spring.chat.domain.Chat
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ChatAPIController(
    private val pubChatting: SendChatUseCase
) {

    @MessageMapping("/chat")
    fun sendChat(chat: ChatRequest) {
        pubChatting(chat.toDomain())
    }

    data class ChatRequest(
        val chatRoomId: UUID,
        val nickname: String,
        val message: String,
    ) {
        fun toDomain() = Chat(
            chatRoomId = chatRoomId,
            nickname = nickname,
            message = message
        )
    }
}