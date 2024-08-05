package operations

import Nibbles
import org.example.Computer

class ConvertByteToAscii : Instruction() {
    var rx: Int = 0
    var ry: Int = 0
    override fun parseBytes(nibbles: Nibbles) {
        rx = nibbles.getSecondNibble().toInt()
        ry = nibbles.getThirdNibble().toInt()
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(computer: Computer) {
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
        val value = computer.cpu.registers[rx].toInt()
        if (value > 15) {
            throw IllegalArgumentException("Value is greater than 15")
        }
        val ascii = value + 48
        computer.cpu.registers[ry] = ascii.toUByte()
    }
}