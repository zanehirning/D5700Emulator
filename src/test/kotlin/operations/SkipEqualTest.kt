package operations

import Nibbles
import org.example.Computer
import kotlin.test.Test

class SkipEqualTest {
    @Test
    fun testParseBytes() {
        val skipEqual = SkipEqual()
        skipEqual.parseBytes(Nibbles(0u, 32u))
        assert(skipEqual.rx == 0)
        assert(skipEqual.ry == 2)
        skipEqual.parseBytes(Nibbles(15u, 32u))
        assert(skipEqual.rx == 15)
        assert(skipEqual.ry == 2)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testPerformOperation() {
        val skipEqual = SkipEqual()
        val computer = Computer()
        computer.cpu.registers[0] = 1u
        computer.cpu.registers[1] = 1u
        skipEqual.rx = 0
        skipEqual.ry = 1
        skipEqual.performOperation(computer)
        assert(computer.cpu.p.toUInt() == 4u)

        computer.cpu.registers[0] = 1u
        computer.cpu.registers[1] = 2u
        skipEqual.rx = 0
        skipEqual.ry = 1
        skipEqual.performOperation(computer)
        assert(computer.cpu.p.toUInt() == 6u)

        computer.cpu.registers[0] = 1u
        skipEqual.rx = 0
        skipEqual.ry = 0
        skipEqual.performOperation(computer)
        assert(computer.cpu.p.toUInt() == 10u)
    }

    @Test
    fun testExecute() {
        val computer = Computer()
        computer.cpu.registers[5] = 1u
        computer.cpu.registers[0] = 1u
        val skipEqual = SkipEqual()
        skipEqual.execute(computer, Nibbles(5u, 0u))
        assert(computer.cpu.p.toUInt() == 4u)

        computer.cpu.registers[5] = 1u
        computer.cpu.registers[0] = 2u
        skipEqual.execute(computer, Nibbles(5u, 1u))
        assert(computer.cpu.p.toUInt() == 6u)

        computer.cpu.registers[5] = 1u
        computer.cpu.registers[1] = 2u
        skipEqual.execute(computer, Nibbles(5u, 16u))
        assert(computer.cpu.p.toUInt() == 8u)

        computer.cpu.registers[5] = 2u
        computer.cpu.registers[1] = 2u
        skipEqual.execute(computer, Nibbles(5u, 16u))
        assert(computer.cpu.p.toUInt() == 12u)

        computer.cpu.registers[0] = 1u
        skipEqual.execute(computer, Nibbles(0u, 0u))
        assert(computer.cpu.p.toUInt() == 16u)
    }
}