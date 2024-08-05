package operations

import Nibbles
import org.example.Computer

class Draw : Instruction() {
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
        val x = computer.cpu.registers[rx]
        if (x > 127u) {
            throw Exception("Cannot draw ascii value greater than 127")
        }
        // draw the ascii value of x at the screen location specified by ry and rz
        computer.screen.memory[ry * 8 + rz] = x
        computer.screen.draw()
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }
}