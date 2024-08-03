package org.example

import java.io.File
import java.io.FileNotFoundException

fun main() {
    println("Please enter the file you would like to run:")
    val file = "roms/" + readLine()
    try {
        Computer(File(file))
    } catch (e: FileNotFoundException) {
        println("Please provide a rom that exists")
    }
}