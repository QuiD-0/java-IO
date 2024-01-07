package com.quid.io.spring.chat.gateway.repository

import com.quid.io.spring.chat.domain.Chat
import com.quid.io.spring.chat.gateway.repository.memory.InMemoryChatHistoryRepository
import org.springframework.stereotype.Repository
import java.util.UUID

interface ChatHistoryRepository {

    fun save(chat: Chat)

    fun findAllByChatRoomId(chatRoomId: UUID): List<Chat>

    @Repository
    class ChatHistoryRepositoryImpl(
        private val chatHistory: InMemoryChatHistoryRepository
    ) : ChatHistoryRepository {

        override fun save(chat: Chat) {
            chatHistory.save(chat)
        }

        override fun findAllByChatRoomId(chatRoomId: UUID): List<Chat> =
            chatHistory.findAllByChatRoomId(chatRoomId)
    }
}