package operations

import Nibbles
import org.example.Computer
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class StoreTest {
    @Test
    @OptIn(ExperimentalUnsignedTypes::class)
    fun testStoreExecute() {
        // 00000000, 00000000
        var computer = Computer()
        var nibbles = Nibbles(0u, 0u)
        Store().execute(computer, nibbles)
        assert(computer.cpu.registers[0].toInt() == 0)
        // 00000001, 00000001
        computer = Computer()
        nibbles = Nibbles(1u, 1u)
        Store().execute(computer, nibbles)
        assert(computer.cpu.registers[1].toInt() == 1)
        // 00000111, 11111111
        computer = Computer()
        nibbles = Nibbles(7u, 255u)
        Store().execute(computer, nibbles)
        assert(computer.cpu.registers[7].toInt() == 255)
        // 11111111, 11111111
        assertThrows<IndexOutOfBoundsException> {
            computer = Computer()
            nibbles = Nibbles(255u, 255u)
            Store().execute(computer, nibbles)
        }
    }

    @Test
    fun testStoreParseBytes() {
        var store = Store()
        store.parseBytes(Nibbles(0u, 0u))
        assert(store.register == 0)
        assert(store.byte.toInt() == 0)
        store.parseBytes(Nibbles(1u, 1u))
        assert(store.register == 1)
        assert(store.byte.toInt() == 1)
        store.parseBytes(Nibbles(7u, 255u))
        assert(store.register == 7)
        assert(store.byte.toInt() == 255)
    }

    @Test
    @OptIn(ExperimentalUnsignedTypes::class)
    fun testStorePerformOperation() {
        var computer = Computer()
        var store = Store()
        store.register = 0
        store.byte = 0u
        store.performOperation(computer)
        assert(computer.cpu.registers[0].toInt() == 0)
        store.register = 1
        store.byte = 1u
        store.performOperation(computer)
        assert(computer.cpu.registers[1].toInt() == 1)
        store.register = 7
        store.byte = 255u
        store.performOperation(computer)
        assert(computer.cpu.registers[7].toInt() == 255)
    }
}
