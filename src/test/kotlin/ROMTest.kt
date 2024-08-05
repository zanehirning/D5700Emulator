import exceptions.InvalidMemoryOperationException
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertFailsWith

class ROMTest {
    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testReadRom() {
        var rom = ROM()
        rom.memory[0] = 15u
        rom.memory[4] = 20u
        rom.memory[3] = UByte.MAX_VALUE
        assert(rom.read(0u).toUInt() == 15u)
        assert(rom.read(4u).toUInt() == 20u)
        assert(rom.read(3u) == UByte.MAX_VALUE)
    }

    @Test
    fun testReadRomOutOfBounds() {
        var rom = ROM()
        assertFailsWith<IndexOutOfBoundsException> {
            rom.read(4000u)
        }
    }

    @Test
    fun testWriteRom() {
        var rom = ROM()
        assertThrows<InvalidMemoryOperationException> {
            rom.write(0u, 15u)
        }
    }
}