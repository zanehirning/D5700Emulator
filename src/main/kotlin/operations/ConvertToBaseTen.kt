package operations

import Nibbles
import org.example.Computer

class ConvertToBaseTen : Instruction() {
    var rx: Int = 0
    override fun parseBytes(nibbles: Nibbles) {
        rx = nibbles.getSecondNibble().toInt()
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(computer: Computer) {
        val register = computer.cpu.registers[rx]
        val baseTen = register.toInt()
        val hundredsPlace = baseTen / 100
        val tensPlace = (baseTen - (hundredsPlace * 100)) / 10
        val onesPlace = baseTen - (hundredsPlace * 100) - (tensPlace * 10)
        computer.memories[computer.cpu.m].write(computer.cpu.a, hundredsPlace.toUByte())
        computer.memories[computer.cpu.m].write((computer.cpu.a + 1u).toUShort(), tensPlace.toUByte())
        computer.memories[computer.cpu.m].write((computer.cpu.a + 2u).toUShort(), onesPlace.toUByte())
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }
}