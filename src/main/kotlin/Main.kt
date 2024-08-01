package org.example

import java.io.FileNotFoundException

fun main() {
    val file = "/roms/" + readLine()
    try {

    } catch (e: FileNotFoundException) {
        println("Please provide a rom that exists")
    }
}