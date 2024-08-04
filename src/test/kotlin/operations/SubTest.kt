package operations

import Nibbles
import org.example.Computer
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class SubTest {

    @Test
    fun testSubExecute() {
        val computer = Computer()
        var nibbles = Nibbles(0u, 0u)
        var sub = Sub()
        sub.execute(computer, nibbles)
        assert(computer.cpu.registers[0].toUInt() == 0u)
        assert(computer.cpu.p.toUInt() == 2u)

        nibbles = Nibbles(1u, 7u)
        computer.cpu.registers[1] = 20u
        computer.cpu.registers[0] = 10u
        sub.execute(computer, nibbles)
        assert(computer.cpu.registers[7].toUInt() == 10u)
        assert(computer.cpu.p.toUInt() == 4u)

        nibbles = Nibbles(7u, 8u)
        assertThrows<ArrayIndexOutOfBoundsException> {
            sub.execute(computer, nibbles)
        }
    }

    @Test
    fun testSubParseBytes() {
        var nibbles = Nibbles(0u, 0u)
        var sub = Sub()
        sub.parseBytes(nibbles)
        assert(sub.rx == 0)
        assert(sub.ry == 0)
        assert(sub.rz == 0)

        nibbles = Nibbles(7u, 0u)
        sub.parseBytes(nibbles)
        assert(sub.rx == 7)
        assert(sub.ry == 0)
        assert(sub.rz == 0)

        nibbles = Nibbles(7u, 8u)
        sub.parseBytes(nibbles)
        assert(sub.rx == 7)
        assert(sub.ry == 0)
        assert(sub.rz == 8)

        nibbles = Nibbles(7u, 30u)
        // 00011110
        sub.parseBytes(nibbles)
        assert(sub.rx == 7)
        assert(sub.ry == 1)
        assert(sub.rz == 14)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testSubPerformOperation() {
        val computer = Computer()
        computer.cpu.registers[0] = 2u
        computer.cpu.registers[1] = 1u
        computer.cpu.registers[2] = 3u
        val sub = Sub()
        sub.rx = 0
        sub.ry = 1
        sub.rz = 2
        sub.performOperation(computer)
        assert(computer.cpu.registers[2].toUInt() == 1u)
        assert(computer.cpu.p.toUInt() == 2u)

        computer.cpu.registers[0] = 255u
        computer.cpu.registers[1] = 1u
        computer.cpu.registers[2] = 0u
        sub.rx = 0
        sub.ry = 1
        sub.rz = 2
        sub.performOperation(computer)
        assert(computer.cpu.registers[2].toUInt() == 254u)
        assert(computer.cpu.p.toUInt() == 4u)
    }

}