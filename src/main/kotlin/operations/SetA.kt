package operations

import Nibbles
import org.example.Computer

class SetA : Instruction() {
    var address: Int = 0
    override fun performOperation(computer: Computer) {
        computer.cpu.a = address.toUShort()
        computer.cpu.p = (computer.cpu.p + 2u).toUShort()
    }

    override fun parseBytes(nibbles: Nibbles) {
        address = nibbles.getAddress(
            nibbles.getSecondNibble(),
            nibbles.getThirdNibble(),
            nibbles.getFourthNibble()
        ).toInt()
    }
}