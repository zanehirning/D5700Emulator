package operations

import Nibbles
import org.example.Computer
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class JumpTest {
    @Test
    fun testJumpExecute() {
        val computer = Computer()
        var nibbles = Nibbles(0u, 0u)
        val jump = Jump()
        jump.execute(computer, nibbles)
        assert(computer.cpu.p.toUInt() == 0u)

        nibbles = Nibbles(1u, 0u)
        jump.execute(computer, nibbles)
        assert(computer.cpu.p.toUInt() == 256u)

        nibbles = Nibbles(1u, 242u)
        // 1F2
        jump.execute(computer, nibbles)
        assert(computer.cpu.p.toUInt() == 498u)

        assertThrows<IllegalArgumentException> {
            jump.execute(computer, Nibbles(1u, 1u))
        }
    }

    @Test
    fun testJumpParseBytes() {
        var nibbles = Nibbles(0u, 0u)
        var jump = Jump()
        jump.parseBytes(nibbles)
        assert(jump.address == 0)

        nibbles = Nibbles(1u, 0u)
        jump = Jump()
        jump.parseBytes(nibbles)
        assert(jump.address == 256)

        nibbles = Nibbles(1u, 242u)
        // 1F2
        jump = Jump()
        jump.parseBytes(nibbles)
        assert(jump.address == 498)

        nibbles = Nibbles(8u, 254u)
        // 8FE
        jump = Jump()
        jump.parseBytes(nibbles)
        assert(jump.address == 2302)
    }

    @Test
    fun testJumpPerformOperation() {
        val computer = Computer()
        val jump = Jump()
        jump.address = 0
        jump.performOperation(computer)
        assert(computer.cpu.p.toUInt() == 0u)

        jump.address = 256
        jump.performOperation(computer)
        assert(computer.cpu.p.toUInt() == 256u)

        jump.address = UShort.MAX_VALUE.toInt() - 1
        jump.performOperation(computer)
        assert(computer.cpu.p.toUInt() == UShort.MAX_VALUE - 1u)

        jump.address = 1
        assertThrows<IllegalArgumentException> {
            jump.performOperation(computer)
        }
    }
}