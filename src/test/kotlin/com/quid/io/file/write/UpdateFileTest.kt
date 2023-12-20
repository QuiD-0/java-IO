package com.quid.io.file.write

import com.quid.io.file.UpdateFile.IOUpdate
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled
class UpdateFileTest{

    @Test
    fun `update file`(){
        val content = "Hello World!"
        IOUpdate()(path, content)

        val file = File(path)
        assertEquals(content, file.readText())
    }

    @BeforeEach
    fun setUp(){
        val file = File(path)
        file.writeText("")
    }

    @AfterEach
    fun tearDown(){
        val file = File(path)
        file.delete()
    }

    companion object{
        private val path = System.getProperty("user.home")+ "/Downloads" + "/test.txt";
    }
}