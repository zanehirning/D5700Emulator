package operations

import Nibbles
import exceptions.InvalidMemoryOperationException
import org.example.Computer
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class WriteTest {
    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testWriteExecute() {
        val computer = Computer()
        val write = Write()
        computer.cpu.registers[0] = 128u
        write.execute(computer, Nibbles(0u, 0u))
        assert(computer.ram.memory[0].toUInt() == 128u)
        assert(computer.cpu.p.toUInt() == 2u)

        computer.cpu.registers[1] = 1u
        computer.cpu.a = 255u
        write.execute(computer, Nibbles(1u, 0u))
        assert(computer.ram.memory[255].toUInt() == 1u)
        assert(computer.cpu.p.toUInt() == 4u)

        computer.cpu.m = 1
        assertThrows<InvalidMemoryOperationException> {
            write.execute(computer, Nibbles(0u, 128u))
        }
    }

    @Test
    fun testWriteParseBytes() {
        val write = Write()
        write.parseBytes(Nibbles(0u, 0u))
        assert(write.rx == 0)
        write.parseBytes(Nibbles(1u, 0u))
        assert(write.rx == 1)
        write.parseBytes(Nibbles(128u, 0u))
        assert(write.rx == 0)
        write.parseBytes(Nibbles(0u, 128u))
        assert(write.rx == 0)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testWritePerformOperation() {
        val computer = Computer()
        val write = Write()
        write.rx = 1
        computer.cpu.registers[write.rx] = 128u
        computer.cpu.a = 255u
        computer.cpu.m = 0
        write.performOperation(computer)
        assert(computer.ram.memory[255].toUInt() == 128u)
        assert(computer.cpu.p.toUInt() == 2u)

        write.rx = 7
        computer.cpu.a = 255u
        computer.cpu.registers[write.rx] = 1u
        computer.cpu.m = 0
        write.performOperation(computer)
        assert(computer.ram.memory[255].toUInt() == 1u)
        assert(computer.cpu.p.toUInt() == 4u)

        computer.cpu.m = 1
        assertThrows<InvalidMemoryOperationException> {
            write.performOperation(computer)
        }
    }
}