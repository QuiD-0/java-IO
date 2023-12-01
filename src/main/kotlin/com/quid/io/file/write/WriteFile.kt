package com.quid.io.file.write

class WriteFile {
    fun txt() {
        val path = System.getProperty("user.home")
        val fileName = "$path/Downloads/test.txt"
        val text = "Hello World!"

        java.io.File(fileName).writeText(text)
    }

}

fun main(){
    WriteFile().txt()
}