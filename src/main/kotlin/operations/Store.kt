package operations

import Nibbles
import org.example.CPU
import org.example.Computer

class Store : Instruction() {
    var register: Int = 0
    var byte: UByte = 0u

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(computer: Computer) {
        computer.cpu.registers[register] = byte
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }

    override fun parseBytes(nibbles: Nibbles) {
        register = nibbles.getSecondNibble().toInt()
        byte = nibbles.b1
    }
}