package operations

import Nibbles
import org.example.Computer
import org.junit.jupiter.api.Test

class SetTTest {
    @Test
    fun testPerformOperation() {
        val setT = SetT()
        val computer = Computer()
        setT.value = 0
        setT.performOperation(computer)
        assert(computer.cpu.t == 0.toUByte())
        assert(computer.cpu.p == 2.toUShort())

        setT.value = UByte.MAX_VALUE.toInt()
        setT.performOperation(computer)
        assert(computer.cpu.t == UByte.MAX_VALUE)
        assert(computer.cpu.p == 4.toUShort())
    }

    @Test
    fun testParseBytes() {
        val setT = SetT()
        var nibbles = Nibbles(0u, 30u)
        // 0000 0001 1110
        setT.parseBytes(nibbles)
        assert(setT.value == 1)

        nibbles = Nibbles(1u, 30u)
        // 0001 0001 1110
        setT.parseBytes(nibbles)
        assert(setT.value == 17)

        nibbles = Nibbles(7u, 30u)
        // 0111 0001 1110
        setT.parseBytes(nibbles)
        assert(setT.value == 113)
    }

    @Test
    fun testExecute() {
        val setT = SetT()
        val computer = Computer()
        var nibbles = Nibbles(0u, 30u)
        // 0000 0001 1110
        setT.execute(computer, nibbles)
        assert(computer.cpu.t == 1.toUByte())
        assert(computer.cpu.p == 2.toUShort())

        nibbles = Nibbles(1u, 30u)
        // 0001 0001 1110
        setT.execute(computer, nibbles)
        assert(computer.cpu.t == 17.toUByte())
        assert(computer.cpu.p == 4.toUShort())

        nibbles = Nibbles(7u, 30u)
        // 0111 0001 1110
        setT.execute(computer, nibbles)
        assert(computer.cpu.t == 113.toUByte())
        assert(computer.cpu.p == 6.toUShort())
    }
}