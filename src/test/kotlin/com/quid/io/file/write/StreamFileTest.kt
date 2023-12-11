package com.quid.io.file.write

import com.quid.io.file.StreamFile.IOStreamFile
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.io.File

class StreamFileTest{

    @Test
    fun `write img`(){
        IOStreamFile().input()

        assert(File(path).exists())
    }

    @Test
    fun `write img with buffer`(){
        IOStreamFile().buffer()

        assert(File(path).exists())
    }

    @Test
    fun speadTest(){
        val start = System.currentTimeMillis()
        repeat(100){
            IOStreamFile().input()
        }
        val end = System.currentTimeMillis()
        println("Time: ${end - start} ms")

        val startBuffer = System.currentTimeMillis()
        repeat(100){
            IOStreamFile().buffer()
        }
        val endBuffer = System.currentTimeMillis()
        println("Time: ${endBuffer - startBuffer} ms")
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