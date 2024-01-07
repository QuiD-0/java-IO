package com.quid.io.spring.chat.gateway.repository.memory

import com.quid.io.spring.chat.domain.Chat
import org.springframework.stereotype.Component
import java.util.*

interface InMemoryChatHistoryRepository {

    fun save(chat: Chat)

    fun findAllByChatRoomId(chatRoomId: UUID): List<Chat>

    @Component
    class InMemoryChatHistoryRepositoryImpl : InMemoryChatHistoryRepository {

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