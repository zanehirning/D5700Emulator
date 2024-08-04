package operations

import Nibbles
import org.example.Computer

class Read : Instruction() {
    var rx: Int = 0
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(computer: Computer) {
        computer.cpu.registers[rx] = computer.memories[computer.cpu.m].read(computer.cpu.a)
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }

    override fun parseBytes(nibbles: Nibbles) {
        rx = nibbles.getSecondNibble().toInt()
    }
}