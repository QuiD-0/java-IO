package com.quid.io.file.write

import java.io.File
import java.nio.file.Files

class WriteFile(
    private val path: String = System.getProperty("user.home"),
    private val fileName: String = "test.txt"
) {

    fun txt() {
        val text = "Hello World!"

        File("$path/Downloads/$fileName").writeText(text)
    }

    fun nio() {
        val text = "Hello World!"

        Files.write(java.nio.file.Paths.get("$path/Downloads/$fileName"), text.toByteArray())
    }
}
