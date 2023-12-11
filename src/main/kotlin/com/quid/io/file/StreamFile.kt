package com.quid.io.file

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

interface StreamFile {
    fun input()
    fun buffer()

    class IOStreamFile(
        private val path: String = System.getProperty("user.home"),
        private val fileName: String = "write.jpg"
    ) : StreamFile {
        override fun input() {
            val image = FileInputStream("src/main/resources/asset/img.jpg")
                .use { it.readBytes() }

            val file = File("$path/Downloads/$fileName")

            FileOutputStream(file)
                .use { it.write(image) }
        }

        override fun buffer() {
            val image = BufferedInputStream(FileInputStream("src/main/resources/asset/img.jpg"))
                .use { it.readBytes() }

            val file = File("$path/Downloads/$fileName")

            BufferedOutputStream(FileOutputStream(file))
                .use { it.write(image) }

        }
    }
}