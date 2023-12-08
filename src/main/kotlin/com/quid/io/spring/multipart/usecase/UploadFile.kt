package com.quid.io.spring.multipart.usecase

import org.springframework.stereotype.Service
import java.io.File

fun interface UploadFile {
    operator fun invoke(file: File, fileName: String)

    @Service
    class IOUploadFile : UploadFile {
        override fun invoke(file: File, fileName: String) {
            val path = System.getProperty("user.home")
            val uploadFile = File("$path/Downloads/$fileName")

            val byte: ByteArray = file.readBytes()
            uploadFile.writeBytes(byte)
        }
    }
}