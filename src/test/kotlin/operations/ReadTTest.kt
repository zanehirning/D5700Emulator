package operations

import Nibbles
import org.example.Computer
import org.junit.jupiter.api.Test

class ReadTTest {
    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testPerformOperation() {
        val readT = ReadT()
        val computer = Computer()
        computer.cpu.t = 5u
        readT.rx = 0
        readT.performOperation(computer)
        assert(computer.cpu.registers[0].toInt() == 5)
        assert(computer.cpu.p.toInt() == 2)

        readT.rx = 1
        readT.performOperation(computer)
        assert(computer.cpu.registers[1].toInt() == 5)
        assert(computer.cpu.p.toInt() == 4)
    }

    @Test
    fun testParseBytes() {
        val readT = ReadT()
        var nibbles = Nibbles(0u, 0u)
        readT.parseBytes(nibbles)
        assert(readT.rx == 0)

        nibbles = Nibbles(7u, 1u)
        readT.parseBytes(nibbles)
        assert(readT.rx == 7)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testExecute() {
        val readT = ReadT()
        val computer = Computer()
        var nibbles = Nibbles(0u, 0u)
        readT.execute(computer, nibbles)
        assert(computer.cpu.registers[0].toInt() == 0)
        assert(computer.cpu.p.toInt() == 2)

        computer.cpu.t = 5u
        nibbles = Nibbles(0u, 1u)
        readT.execute(computer, nibbles)
        assert(computer.cpu.registers[0].toInt() == 5)
        assert(computer.cpu.p.toInt() == 4)

        nibbles = Nibbles(7u, 1u)
        readT.execute(computer, nibbles)
        assert(computer.cpu.registers[7].toInt() == 5)
        assert(computer.cpu.p.toInt() == 6)
    }
}