package org.example

abstract class Memory {
    val memory = UByteArray(4000)
    fun read(address: UShort): UByte {
        return memory[address.toInt()]
    }
    abstract fun write(address: UShort, value: UByte)
}