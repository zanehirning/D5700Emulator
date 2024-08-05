package operations

import Nibbles
import org.example.Computer
import kotlin.test.Test

class SetATest {

    @Test
    fun testPerformOperation() {
        val setA = SetA()
        val computer = Computer()
        setA.address = 0
        setA.performOperation(computer)
        assert(computer.cpu.a == 0.toUShort())
        assert(computer.cpu.p == 2.toUShort())

        setA.address = UShort.MAX_VALUE.toInt()
        setA.performOperation(computer)
        assert(computer.cpu.a == UShort.MAX_VALUE)
        assert(computer.cpu.p == 2.toUShort())
    }

    @Test
    fun testParseBytes() {
        val setA = SetA()
        val nibbles = Nibbles(0u, 30u)
        // 0000 0001 1110
        setA.parseBytes(nibbles)
        assert(setA.address == 30)

        val nibbles2 = Nibbles(1u, 30u)
        // 0001 0001 1110
        setA.parseBytes(nibbles2)
        assert(setA.address == 286)
    }

    @Test
    fun testExecute() {
        val setA = SetA()
        val computer = Computer()
        val nibbles = Nibbles(0u, 30u)
        // 0000 0001 1110
        setA.execute(computer, nibbles)
        assert(computer.cpu.a == 30.toUShort())
        assert(computer.cpu.p == 2.toUShort())

        val nibbles2 = Nibbles(1u, 30u)
        // 0001 0001 1110
        setA.execute(computer, nibbles2)
        assert(computer.cpu.a == 286.toUShort())
        assert(computer.cpu.p == 4.toUShort())
    }
}