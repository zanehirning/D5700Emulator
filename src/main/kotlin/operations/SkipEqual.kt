package operations

import Nibbles
import org.example.Computer

class SkipEqual : Instruction() {
    var rx: Int = 0
    var ry: Int = 0
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(computer: Computer) {
        if (computer.cpu.registers[rx] == computer.cpu.registers[ry]) {
            computer.cpu.p = (computer.cpu.p + 2u).toUShort()
        }
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }
    override fun parseBytes(nibbles: Nibbles) {
        rx = nibbles.getSecondNibble().toInt()
        ry = nibbles.getThirdNibble().toInt()
    }
}