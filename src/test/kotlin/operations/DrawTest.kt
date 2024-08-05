package operations

import Nibbles
import org.example.Computer
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class DrawTest {

    @Test
    fun testParseBytes() {
        val draw = Draw()
        draw.parseBytes(Nibbles(0u, 0u))
        assert(draw.rx == 0)
        assert(draw.ry == 0)
        assert(draw.rz == 0)

        draw.parseBytes(Nibbles(1u, 17u))
        assert(draw.rx == 1)
        assert(draw.ry == 1)
        assert(draw.rz == 1)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testPerformOperation() {
        val draw = Draw()
        val computer = Computer()
        computer.cpu.registers[0] = 123u
        draw.rx = 0
        draw.ry = 0
        draw.rz = 0
        draw.performOperation(computer)
        assert(computer.screen.memory[0].toUInt() == 123u)
        assert(computer.cpu.p.toInt() == 2)

        computer.cpu.registers[1] = 10u
        draw.rx = 1
        draw.ry = 1
        draw.rz = 1
        draw.performOperation(computer)
        assert(computer.screen.memory[9].toUInt() == 10u)
        assert(computer.cpu.p.toInt() == 4)

        computer.cpu.registers[2] = 128u
        draw.rx = 2
        draw.ry = 2
        draw.rz = 2
        assertThrows<Exception> {
            draw.performOperation(computer)
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testExecute() {
        val draw = Draw()
        val computer = Computer()
        val nibbles = Nibbles(0u, 0u)
        draw.execute(computer, nibbles)
        assert(computer.screen.memory[0].toUInt() == 0u)
        assert(computer.cpu.p.toInt() == 2)
    }
}