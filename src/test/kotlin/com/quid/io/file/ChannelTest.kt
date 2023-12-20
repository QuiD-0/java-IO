package com.quid.io.file

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class ChannelTest {

    @Test
    fun fileChannel() {
        val home: String = System.getProperty("user.home")
        Channel().writeChannel("$home/Desktop/test.txt")
    }
}