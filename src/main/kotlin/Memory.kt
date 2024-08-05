package org.example

abstract class Memory {
    val memory = UByteArray(4000)
    fun read(address: UShort): UByte {
        if (address.toInt() >= memory.size) {
            throw IndexOutOfBoundsException("Attempted to read from out of bounds memory address")
        }
        return memory[address.toInt()]
    }
    abstract fun write(address: UShort, value: UByte)
}