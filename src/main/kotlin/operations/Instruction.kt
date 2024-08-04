package operations

import Nibbles
import org.example.CPU

abstract class Instruction {
    fun execute(cpu: CPU, nibbles: Nibbles) {
        parseBytes(nibbles)
        performOperation(cpu)
    }
    abstract fun parseBytes(nibbles: Nibbles)
    abstract fun performOperation(cpu: CPU)
}
