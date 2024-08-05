package operations

import Nibbles
import org.example.Computer

class SetT : Instruction() {
    var value: Int = 0
    override fun performOperation(computer: Computer) {
        computer.cpu.t = value.toUByte()
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }

    override fun parseBytes(nibbles: Nibbles) {
        value = nibbles.combineNibbles(
            nibbles.getSecondNibble(),
            nibbles.getThirdNibble()
        )
    }
}
