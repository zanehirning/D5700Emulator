package operations

import Nibbles
import org.example.CPU
import org.example.Computer
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class AddTest {

    @Test
    fun testAddExecute() {
        val computer = Computer()
        var nibbles = Nibbles(0u, 0u)
        var add = Add()
        add.execute(computer, nibbles)
        assert(computer.cpu.registers[0].toUInt() == 0u)
        assert(computer.cpu.p.toUInt() == 2u)

        nibbles = Nibbles(1u, 7u)
        computer.cpu.registers[1] = 10u
        computer.cpu.registers[0] = 20u
        add.execute(computer, nibbles)
        assert(computer.cpu.registers[7].toUInt() == 30u)
        assert(computer.cpu.p.toUInt() == 4u)

        assertThrows<ArrayIndexOutOfBoundsException> {
            nibbles = Nibbles(7u, 8u)
            add.execute(computer, nibbles)
        }

        assertThrows<ArrayIndexOutOfBoundsException> {
            nibbles = Nibbles(7u, 255u)
            add.execute(computer, nibbles)
        }
    }

    @Test
    fun testAddParseBytes() {
        var nibbles = Nibbles(0u, 0u)
        var add = Add()
        add.parseBytes(nibbles)
        assert(add.rx == 0)
        assert(add.ry == 0)
        assert(add.rz == 0)

        nibbles = Nibbles(7u, 0u)
        add.parseBytes(nibbles)
        assert(add.rx == 7)
        assert(add.ry == 0)
        assert(add.rz == 0)

        nibbles = Nibbles(7u, 8u)
        add.parseBytes(nibbles)
        assert(add.rx == 7)
        assert(add.ry == 0)
        assert(add.rz == 8)

        nibbles = Nibbles(7u, 30u)
        // 00011110
        add.parseBytes(nibbles)
        assert(add.rx == 7)
        assert(add.ry == 1)
        assert(add.rz == 14)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testAddPerformOperation() {
        val computer = Computer()
        computer.cpu.registers[0] = 1u
        computer.cpu.registers[1] = 2u
        computer.cpu.registers[2] = 3u
        val add = Add()
        add.rx = 0
        add.ry = 1
        add.rz = 2
        add.performOperation(computer)
        assert(computer.cpu.registers[2].toUInt() == 3u)
        assert(computer.cpu.p.toUInt() == 2u)

        computer.cpu.registers[0] = 255u
        computer.cpu.registers[1] = 1u
        computer.cpu.registers[2] = 0u
        add.rx = 0
        add.ry = 1
        add.rz = 2
        add.performOperation(computer)
        assert(computer.cpu.registers[2].toUInt() == 0u)
        assert(computer.cpu.p.toUInt() == 4u)
    }
}