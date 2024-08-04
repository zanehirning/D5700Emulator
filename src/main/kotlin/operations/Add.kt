package operations

import Nibbles
import org.example.Computer

class Add : Instruction() {
    var rx: Int = 0
    var ry: Int = 0
    var rz: Int = 0
    override fun parseBytes(nibbles: Nibbles) {
        rx = nibbles.getSecondNibble().toInt()
        ry = nibbles.getThirdNibble().toInt()
        rz = nibbles.getFourthNibble().toInt()
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(computer: Computer) {
        computer.cpu.registers[rz] = (computer.cpu.registers[rx] + computer.cpu.registers[ry]).toUByte()
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }
}