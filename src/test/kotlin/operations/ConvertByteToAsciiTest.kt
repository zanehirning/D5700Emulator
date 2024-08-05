package operations

import Nibbles
import org.example.Computer
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class ConvertByteToAsciiTest {

    @Test
    fun testPerformOperation() {
        val convertByteToAscii = ConvertByteToAscii()
        val computer = Computer()
        computer.cpu.registers[0] = 0u
        convertByteToAscii.rx = 0
        convertByteToAscii.ry = 1
        convertByteToAscii.performOperation(computer)
        assert(computer.cpu.registers[1].toInt() == 48)
        assert(computer.cpu.p.toInt() == 2)

        computer.cpu.registers[0] = 15u
        convertByteToAscii.rx = 0
        convertByteToAscii.ry = 1
        convertByteToAscii.performOperation(computer)
        assert(computer.cpu.registers[1].toInt() == 63)
        assert(computer.cpu.p.toInt() == 4)
    }

    @Test
    fun testParseBytes() {
        val convertByteToAscii = ConvertByteToAscii()
        var nibbles = Nibbles(0u, 0u)
        convertByteToAscii.parseBytes(nibbles)
        assert(convertByteToAscii.rx == 0)
        assert(convertByteToAscii.ry == 0)

        nibbles = Nibbles(7u, 16u)
        convertByteToAscii.parseBytes(nibbles)
        assert(convertByteToAscii.rx == 7)
        assert(convertByteToAscii.ry == 1)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testExecute() {
        val convertByteToAscii = ConvertByteToAscii()
        val computer = Computer()
        var nibbles = Nibbles(0u, 0u)
        computer.cpu.registers[0] = 100u
        assertThrows<IllegalArgumentException> {
            convertByteToAscii.execute(computer, nibbles)
        }

        computer.cpu.registers[0] = 15u
        convertByteToAscii.execute(computer, nibbles)
        assert(computer.cpu.registers[convertByteToAscii.ry].toUInt() == 63u)
    }
}