package com.quid.io.file

import java.io.File

fun interface UpdateFile {
    operator fun invoke(path: String, content: String)

    class IOUpdate : UpdateFile {
        override fun invoke(path: String, content: String) {
            val file = File(path)
            file.writeText(content)
        }
    }
}