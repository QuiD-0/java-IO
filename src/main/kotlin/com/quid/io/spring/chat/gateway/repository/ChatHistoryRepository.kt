package com.quid.io.spring.chat.gateway.repository

import com.quid.io.spring.chat.domain.Chat
import org.springframework.stereotype.Repository
import java.util.UUID

interface ChatHistoryRepository {

    fun save(chat: Chat)

    fun findAllByChatRoomId(chatRoomId: UUID): List<Chat>

    @Repository
    class InMemoryChatHistoryRepository : ChatHistoryRepository {

        private val chatHistory = mutableMapOf<UUID, Chat>()

        override fun save(chat: Chat) {
            chatHistory[chat.chatRoomId] = chat
        }

        override fun findAllByChatRoomId(chatRoomId: UUID): List<Chat> =
            chatHistory[chatRoomId]
                ?.let { listOf(it) }
                ?: listOf()

    }
}