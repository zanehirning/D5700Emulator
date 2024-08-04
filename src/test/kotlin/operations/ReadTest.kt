package operations

import Nibbles
import org.example.Computer
import kotlin.test.Test

class ReadTest {
    @Test
    fun testPerformOperation() {
        val computer = Computer()
        val read = Read()
        computer.cpu.a = 0u
        computer.cpu.m = 0
        computer.ram.memory[0] = 255u.toUByte()
        read.rx = 0
        read.performOperation(computer)
        assert(computer.cpu.registers[0].toUInt() == 255u)
        assert(computer.cpu.p == 2u.toUShort())

        computer.cpu.a = 1u
        computer.cpu.m = 0
        computer.ram.memory[1] = 254u.toUByte()
        read.rx = 1
        read.performOperation(computer)
        assert(computer.cpu.registers[1].toUInt() == 254u)
        assert(computer.cpu.p == 4u.toUShort())

        computer.cpu.a = 2u
        computer.cpu.m = 1
        computer.rom.memory[2] = 253u.toUByte()
        read.rx = 2
        read.performOperation(computer)
        assert(computer.cpu.registers[2].toUInt() == 253u)
        assert(computer.cpu.p == 6u.toUShort())

        computer.cpu.a = 0u
        read.rx = 0
        read.performOperation(computer)
        assert(computer.cpu.registers[0].toUInt() == 0u)
        assert(computer.cpu.p == 8u.toUShort())
    }

    @Test
    fun testParseBytes() {
        val read = Read()
        var nibbles = Nibbles(9u, 0u)
        read.parseBytes(nibbles)
        assert(read.rx == 9)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testExecute() {
        val computer = Computer()
        var nibbles = Nibbles(0u, 0u)
        val read = Read()
        computer.cpu.a = 0u
        computer.ram.memory[0] = 255u.toUByte()
        read.execute(computer, nibbles)
        assert(computer.cpu.registers[0].toUInt() == 255u)
        assert(computer.cpu.p == 2u.toUShort())

        nibbles = Nibbles(1u, 0u)
        computer.cpu.a = 1u
        computer.ram.memory[1] = 254u.toUByte()
        read.execute(computer, nibbles)
        assert(computer.cpu.registers[1].toUInt() == 254u)
        assert(computer.cpu.p == 4u.toUShort())

        nibbles = Nibbles(2u, 0u)
        computer.cpu.a = 2u
        computer.cpu.m = 1
        computer.rom.memory[2] = 253u.toUByte()
        read.execute(computer, nibbles)
        assert(computer.cpu.registers[2].toUInt() == 253u)
        assert(computer.cpu.p == 6u.toUShort())

        nibbles = Nibbles(0u, 0u)
        computer.cpu.a = 0u
        read.execute(computer, nibbles)
        assert(computer.cpu.registers[0].toUInt() == 0u)
        assert(computer.cpu.p == 8u.toUShort())
    }
}