package operations

import Nibbles
import org.example.Computer

class Write : Instruction() {
    var rx: Int = 0
    override fun parseBytes(nibbles: Nibbles) {
        rx = nibbles.getSecondNibble().toInt()
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(computer: Computer) {
        computer.memories[computer.cpu.m].write(computer.cpu.a, computer.cpu.registers[rx])
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }
}