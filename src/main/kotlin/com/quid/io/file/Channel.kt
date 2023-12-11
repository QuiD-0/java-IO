package com.quid.io.file

import java.nio.channels.FileChannel
import java.nio.channels.FileChannel.MapMode.READ_WRITE
import java.nio.file.Path
import java.nio.file.StandardOpenOption

class Channel {

    fun fileChannel(path: String) {
        val fileChannel = FileChannel.open(Path.of(path), StandardOpenOption.READ, StandardOpenOption.WRITE)

        fileChannel.use {
            val text = "Hello, World!"
            val buffer = it.map(READ_WRITE, 0, text.length.toLong())
                .apply { put(text.toByteArray()) }

            it.write(buffer)
        }
    }
}
