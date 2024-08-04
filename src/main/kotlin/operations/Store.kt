package operations

import Nibbles
import org.example.CPU

class Store : Instruction() {
    var register: Int = 0
    var byte: UByte = 0u

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(cpu: CPU) {
        cpu.registers[register] = byte
    }

    override fun parseBytes(nibbles: Nibbles) {
        register = nibbles.getSecondNibble().toInt()
        byte = nibbles.b1
    }
}