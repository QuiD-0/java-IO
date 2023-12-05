package com.quid.io.file.write

import java.io.File
import java.io.FileInputStream

interface StreamFile {
    fun input()

    class IOStreamFile(
        private val path: String = System.getProperty("user.home"),
        private val fileName: String = "write.jpg"
    ) : StreamFile {
        override fun input() {
            val inputStream = FileInputStream("src/main/resources/asset/img.jpg")
            val image = inputStream.readBytes()

            val file = File("$path/Downloads/$fileName")
            file.writeBytes(image)
        }
    }
}