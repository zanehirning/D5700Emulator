package org.example

class CPU {
    var p: UShort = 0u
    var t: UByte = 0u
    var a: UShort = 0u
    var m: Int = 0
    @OptIn(ExperimentalUnsignedTypes::class)
    var registers = UByteArray(8)
}