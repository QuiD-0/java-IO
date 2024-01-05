package com.quid.io.spring.chat.domain

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.UUID

data class ChatRoom (
    val id: UUID = UUID.randomUUID(),
    val name: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val regDate: LocalDateTime = LocalDateTime.now(),
){
}