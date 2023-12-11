package com.quid.io.file

import org.junit.jupiter.api.Test

class ChannelTest {

    @Test
    fun fileChannel() {
        val home: String = System.getProperty("user.home")
        Channel().writeChannel("$home/Desktop/test.txt")
    }
}