package com.quid.io.file.write

import com.quid.io.file.write.StreamFile.IOStreamFile
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.io.File

class StreamFileTest{

    @Test
    fun `write img`(){
        IOStreamFile().input()

        assert(File(path).exists())
    }

    @AfterEach
    fun tearDown(){
        val file = File(path)
        file.delete()
    }

    companion object{
        private val path = System.getProperty("user.home")+ "/Downloads" + "/write.jpg";
    }
}