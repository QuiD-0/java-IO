package com.quid.io.spring.chat.gateway.socket

import com.quid.io.spring.chat.domain.Chat
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

interface ChatStompSender {

    fun sendChat(chat: Chat)

    @Component
    class StompChatStompSender(
        private val stompSender: SimpMessagingTemplate
    ) : ChatStompSender {

        override fun sendChat(chat: Chat) {
            stompSender.convertAndSend("/sub/chat/${chat.chatRoomId}", chat)
        }
    }
}