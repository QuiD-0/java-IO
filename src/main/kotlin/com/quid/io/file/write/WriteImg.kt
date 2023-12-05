package com.quid.io.file.write

import java.io.File

class WriteImg(
    private val path: String = System.getProperty("user.home"),
    private val fileName: String = "image.jpg"
) {

        fun img() {
            val image = File("src/main/resources/asset/img.jpg").readBytes()

            File("$path/Downloads/$fileName").writeBytes(image)
        }

}
