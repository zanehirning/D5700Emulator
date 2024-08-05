package operations

import Nibbles
import org.example.Computer

class ReadKeyboard : Instruction() {
    var rx: Int = 0
    override fun parseBytes(nibbles: Nibbles) {
        rx = nibbles.getSecondNibble().toInt()
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(computer: Computer) {
        println("Waiting for keyboard input")
        var input = readlnOrNull()
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
        if (input == null || input == "") {
            computer.cpu.registers[rx] = 0u
            return
        }
        if (input.length > 2) {
            input = input.substring(0, 2)
        }
        if (!input.matches(Regex("[0-9A-Fa-f]+"))) {
            throw IllegalArgumentException("Invalid input")
        }
        computer.cpu.registers[rx] = input.lowercase().toInt(16).toUByte()
    }
}

