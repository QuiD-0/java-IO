package com.quid.io.spring.chat.domain

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

class Chat(
    val id: UUID = UUID.randomUUID(),
    val chatRoomId: String,
    val name: String,
    val message: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val timeStamp: LocalDateTime = LocalDateTime.now(),
) {
}