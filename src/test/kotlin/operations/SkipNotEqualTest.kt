package operations

import Nibbles
import org.example.Computer
import kotlin.test.Test

class SkipNotEqualTest {
    @Test
    fun testParseBytes() {
        val skipNotEqual = SkipNotEqual()
        skipNotEqual.parseBytes(Nibbles(0u, 32u))
        assert(skipNotEqual.rx == 0)
        assert(skipNotEqual.ry == 2)
        skipNotEqual.parseBytes(Nibbles(15u, 32u))
        assert(skipNotEqual.rx == 15)
        assert(skipNotEqual.ry == 2)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testPerformOperation() {
        val skipNotEqual = SkipNotEqual()
        val computer = Computer()
        computer.cpu.registers[0] = 1u
        computer.cpu.registers[1] = 1u
        skipNotEqual.rx = 0
        skipNotEqual.ry = 1
        skipNotEqual.performOperation(computer)
        assert(computer.cpu.p.toUInt() == 2u)

        computer.cpu.registers[0] = 1u
        computer.cpu.registers[1] = 2u
        skipNotEqual.rx = 0
        skipNotEqual.ry = 1
        skipNotEqual.performOperation(computer)
        assert(computer.cpu.p.toUInt() == 6u)

        computer.cpu.registers[0] = 1u
        skipNotEqual.rx = 0
        skipNotEqual.ry = 0
        skipNotEqual.performOperation(computer)
        assert(computer.cpu.p.toUInt() == 8u)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testExecute() {
        val computer = Computer()
        computer.cpu.registers[5] = 1u
        computer.cpu.registers[0] = 1u
        val skipNotEqual = SkipNotEqual()
        skipNotEqual.execute(computer, Nibbles(5u, 0u))
        assert(computer.cpu.p.toUInt() == 2u)

        computer.cpu.registers[5] = 1u
        computer.cpu.registers[1] = 2u
        skipNotEqual.execute(computer, Nibbles(5u, 16u))
        assert(computer.cpu.p.toUInt() == 6u)

        computer.cpu.registers[5] = 1u
        computer.cpu.registers[1] = 2u
        skipNotEqual.execute(computer, Nibbles(5u, 16u))
        assert(computer.cpu.p.toUInt() == 10u)
    }
}