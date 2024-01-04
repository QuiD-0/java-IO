package com.quid.io.spring.chat

import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

interface SendChatUseCase {

    operator fun invoke()


    @Service
    class StompSendChatUseCase(
        private val message: SimpMessageSendingOperations
    ) : SendChatUseCase {

        override fun invoke() {
            message.convertAndSend("/pub/topic/chat", "hello")
        }
    }
}