package operations

import Nibbles
import org.example.Computer

abstract class Instruction {
    fun execute(computer: Computer, nibbles: Nibbles) {
        parseBytes(nibbles)
        performOperation(computer)
    }
    abstract fun parseBytes(nibbles: Nibbles)
    abstract fun performOperation(computer: Computer)
}
