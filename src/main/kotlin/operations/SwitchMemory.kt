package operations

import Nibbles
import org.example.Computer

class SwitchMemory : Instruction() {
    override fun parseBytes(nibbles: Nibbles) {
        // No operation
    }

    override fun performOperation(computer: Computer) {
        computer.cpu.m = if (computer.cpu.m == 0) 1 else 0
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }
}