package com.quid.io.spring.chat.domain

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.UUID

class ChatRoom (
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val count: Int = 0,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val regDate: LocalDateTime = LocalDateTime.now(),
){
}