package operations

import Nibbles
import org.example.Computer

class Jump : Instruction() {
    var address: Int = 0
    override fun parseBytes(nibbles: Nibbles) {
        address = nibbles.getAddress(
            nibbles.getSecondNibble(),
            nibbles.getThirdNibble(),
            nibbles.getFourthNibble()
        ).toInt()
    }

    override fun performOperation(computer: Computer) {
        if (address % 2 != 0) {
            throw IllegalArgumentException("Jump address must be even")
        }
        computer.cpu.p = address.toUShort()
    }
}