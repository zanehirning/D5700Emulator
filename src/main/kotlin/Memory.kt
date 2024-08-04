package org.example

abstract class Memory {
    val memory = UByteArray(4000)
    fun read(address: Int): UByte {
        return memory[address]
    }
    abstract fun write()
}