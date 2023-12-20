package com.quid.io.file

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class WatchDirTest{

    @Test
    fun watch(){
        val home: String = System.getProperty("user.home")

        val watchFile = WatchDir()
        watchFile.watch("$home/Downloads")
    }

}