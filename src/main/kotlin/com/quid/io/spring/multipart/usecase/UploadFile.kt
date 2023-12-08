package com.quid.io.spring.multipart.usecase

import org.springframework.stereotype.Service
import java.io.File

fun interface UploadFile {
    operator fun invoke(file: File)

    @Service
    class IOUploadFile : UploadFile {
        override fun invoke(file: File) {
            val path = System.getProperty("user.home")
            val fileName = file.name
            val image = file.readBytes()

            val uploadFile = File("$path/Downloads/$fileName")
            uploadFile.writeBytes(image)
        }
    }
}