package com.quid.io.spring.chat.usecase

import com.quid.io.spring.chat.domain.Chat
import com.quid.io.spring.chat.gateway.repository.ChatHistoryRepository
import com.quid.io.spring.chat.gateway.socket.ChatStompSender
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

interface SendChatUseCase {

    operator fun invoke(chat: Chat)


    @Service
    class StompSendChatUseCase(
        private val sender: ChatStompSender,
        private val chatHistory: ChatHistoryRepository
    ) : SendChatUseCase {

        override fun invoke(chat: Chat) =
            sender.sendChat(chat)
                .also { chatHistory.save(chat) }
    }
}