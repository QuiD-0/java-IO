package com.quid.io.file.write

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class WriteFileTest{

    @Test
    fun `speed test`(){
        val start = System.currentTimeMillis()
        repeat(10000){
            WriteFile(fileName = "io.txt").txt()
        }
        val end = System.currentTimeMillis()
        println("io: ${end - start} ms")

        val start2 = System.currentTimeMillis()
        repeat(10000){
            WriteFile(fileName = "nio.txt").nio()
        }
        val end2 = System.currentTimeMillis()
        println("nio: ${end2 - start2} ms")
    }

    @Test
    fun `parallel speed test`(){
        val start = System.currentTimeMillis()
        repeat(10000){
            Thread{
                WriteFile(fileName = "io.txt").txt()
            }.start()
        }
        val end = System.currentTimeMillis()
        println("io: ${end - start} ms")

        val start2 = System.currentTimeMillis()
        repeat(10000){
            Thread{
                WriteFile(fileName = "nio.txt").nio()
            }.start()
        }
        val end2 = System.currentTimeMillis()
        println("nio: ${end2 - start2} ms")
    }

    @AfterEach
    fun `delete files`(){
        val io = java.io.File(io)
        val nio = java.io.File(nio)

        io.delete()
        nio.delete()

    }

    companion object{
        private val io = System.getProperty("user.home")+ "/Downloads" + "/io.txt";
        private val nio = System.getProperty("user.home")+ "/Downloads" + "/nio.txt";
    }
}