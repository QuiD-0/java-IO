package com.quid.io.spring.chat

import com.quid.io.spring.chat.domain.Chat
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

interface SendChatUseCase {

    operator fun invoke(chat: Chat)


    @Service
    class StompSendChatUseCase(
        private val template: SimpMessagingTemplate
    ) : SendChatUseCase {

        override fun invoke(chat: Chat) {
            template.convertAndSend("/sub/chat/"+chat.chatRoomId, chat)
        }
    }
}