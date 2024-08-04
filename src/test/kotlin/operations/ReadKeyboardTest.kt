package operations

import Nibbles
import org.example.Computer
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class ReadKeyboardTest {

    @Test
    fun testParseBytes() {
        val readKeyboard = ReadKeyboard()
        readKeyboard.parseBytes(Nibbles(0u, 2u))
        assert(readKeyboard.rx == 0)
        readKeyboard.parseBytes(Nibbles(15u, 2u))
        assert(readKeyboard.rx == 15)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testPerformOperation() {
        val readKeyboard = ReadKeyboard()
        val computer = Computer()
        var inputStream = "0F".byteInputStream()
        System.setIn(inputStream)
        readKeyboard.performOperation(computer)
        assert(computer.cpu.registers[0] == 15u.toUByte())
        assert(computer.cpu.p.toUInt() == 2u)

        inputStream = "FF".byteInputStream()
        System.setIn(inputStream)
        readKeyboard.performOperation(computer)
        assert(computer.cpu.registers[0] == 255u.toUByte())
        assert(computer.cpu.p.toUInt() == 4u)

        inputStream = "0".byteInputStream()
        System.setIn(inputStream)
        readKeyboard.performOperation(computer)
        assert(computer.cpu.registers[0] == 0u.toUByte())
        assert(computer.cpu.p.toUInt() == 6u)

        inputStream = "FFF".byteInputStream()
        System.setIn(inputStream)
        readKeyboard.performOperation(computer)
        assert(computer.cpu.registers[0] == 255u.toUByte())
        assert(computer.cpu.p.toUInt() == 8u)

        inputStream = "".byteInputStream()
        System.setIn(inputStream)
        readKeyboard.performOperation(computer)
        assert(computer.cpu.registers[0] == 0u.toUByte())
        assert(computer.cpu.p.toUInt() == 10u)

        inputStream = "G".byteInputStream()
        System.setIn(inputStream)
        assertThrows<IllegalArgumentException> {
            readKeyboard.performOperation(computer)
            assert(computer.cpu.p.toUInt() == 12u)
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testExecute() {
        val readKeyboard = ReadKeyboard()
        val computer = Computer()
        var inputStream = "0F".byteInputStream()
        System.setIn(inputStream)
        var nibbles = Nibbles(0u, 2u)
        readKeyboard.execute(computer, nibbles)
        assert(computer.cpu.registers[0] == 15u.toUByte())
        assert(computer.cpu.p.toUInt() == 2u)

        inputStream = "FF".byteInputStream()
        System.setIn(inputStream)
        nibbles = Nibbles(7u, 2u)
        readKeyboard.execute(computer, nibbles)
        assert(computer.cpu.registers[7] == 255u.toUByte())
        assert(computer.cpu.p.toUInt() == 4u)

        inputStream = "0".byteInputStream()
        System.setIn(inputStream)
        nibbles = Nibbles(4u, 0u)
        readKeyboard.execute(computer, nibbles)
        assert(computer.cpu.registers[4] == 0u.toUByte())
        assert(computer.cpu.p.toUInt() == 6u)

        inputStream = "FFG".byteInputStream()
        System.setIn(inputStream)
        nibbles = Nibbles(0u, 2u)
        readKeyboard.execute(computer, nibbles)
        assert(computer.cpu.registers[0] == 255u.toUByte())
        assert(computer.cpu.p.toUInt() == 8u)

        assertThrows<IllegalArgumentException> {
            inputStream = "G".byteInputStream()
            System.setIn(inputStream)
            nibbles = Nibbles(0u, 2u)
            readKeyboard.execute(computer, nibbles)
            assert(computer.cpu.p.toUInt() == 10u)
        }
    }

}