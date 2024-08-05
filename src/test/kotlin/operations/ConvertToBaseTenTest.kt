package operations

import Nibbles
import org.example.Computer
import kotlin.test.Test


class ConvertToBaseTenTest {
    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testPerformOperation() {
        val convertToBaseTen = ConvertToBaseTen()
        val computer = Computer()
        computer.cpu.registers[0] = 123u
        convertToBaseTen.rx = 0
        convertToBaseTen.performOperation(computer)
        assert(computer.memories[computer.cpu.m].read(computer.cpu.a).toInt() == 1)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 1u).toUShort()).toInt() == 2)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 2u).toUShort()).toInt() == 3)
        assert(computer.cpu.p.toInt() == 2)

        computer.cpu.registers[1] = 1u
        convertToBaseTen.rx = 1
        convertToBaseTen.performOperation(computer)
        assert(computer.memories[computer.cpu.m].read(computer.cpu.a).toInt() == 0)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 1u).toUShort()).toInt() == 0)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 2u).toUShort()).toInt() == 1)
        assert(computer.cpu.p.toInt() == 4)

        computer.cpu.registers[2] = 15u
        convertToBaseTen.rx = 2
        convertToBaseTen.performOperation(computer)
        assert(computer.memories[computer.cpu.m].read(computer.cpu.a).toInt() == 0)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 1u).toUShort()).toInt() == 1)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 2u).toUShort()).toInt() == 5)
        assert(computer.cpu.p.toInt() == 6)
    }

    @Test
    fun testParseBytes() {
        val convertToBaseTen = ConvertToBaseTen()
        var nibbles = Nibbles(0u, 0u)
        convertToBaseTen.parseBytes(nibbles)
        assert(convertToBaseTen.rx == 0)

        nibbles = Nibbles(7u, 1u)
        convertToBaseTen.parseBytes(nibbles)
        assert(convertToBaseTen.rx == 7)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testExecute() {
        val convertToBaseTen = ConvertToBaseTen()
        val computer = Computer()
        var nibbles = Nibbles(0u, 0u)
        convertToBaseTen.execute(computer, nibbles)
        assert(computer.memories[computer.cpu.m].read(computer.cpu.a).toInt() == 0)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 1u).toUShort()).toInt() == 0)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 2u).toUShort()).toInt() == 0)
        assert(computer.cpu.p.toInt() == 2)

        computer.cpu.registers[0] = 123u
        nibbles = Nibbles(0u, 1u)
        convertToBaseTen.execute(computer, nibbles)
        assert(computer.memories[computer.cpu.m].read(computer.cpu.a).toInt() == 1)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 1u).toUShort()).toInt() == 2)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 2u).toUShort()).toInt() == 3)
        assert(computer.cpu.p.toInt() == 4)

        computer.cpu.registers[7] = 1u
        nibbles = Nibbles(7u, 1u)
        convertToBaseTen.execute(computer, nibbles)
        assert(computer.memories[computer.cpu.m].read(computer.cpu.a).toInt() == 0)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 1u).toUShort()).toInt() == 0)
        assert(computer.memories[computer.cpu.m].read((computer.cpu.a + 2u).toUShort()).toInt() == 1)
        assert(computer.cpu.p.toInt() == 6)
    }
}