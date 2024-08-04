package operations

import Nibbles
import org.example.CPU
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class StoreTest {
    @Test
    @OptIn(ExperimentalUnsignedTypes::class)
    fun testStore() {
        // 00000000, 00000000
        var cpu = CPU()
        var nibbles = Nibbles(0u, 0u)
        Store().execute(cpu, nibbles)
        assert(cpu.registers[0].toInt() == 0)
        // 00000001, 00000001
        cpu = CPU()
        nibbles = Nibbles(1u, 1u)
        Store().execute(cpu, nibbles)
        assert(cpu.registers[1].toInt() == 1)
        // 00000111, 11111111
        cpu = CPU()
        nibbles = Nibbles(7u, 255u)
        Store().execute(cpu, nibbles)
        assert(cpu.registers[7].toInt() == 255)
        // 11111111, 11111111
        assertThrows<IndexOutOfBoundsException> {
            cpu = CPU()
            nibbles = Nibbles(255u, 255u)
            Store().execute(cpu, nibbles)
            assert(cpu.registers[15].toInt() == 255)
        }
    }
}
