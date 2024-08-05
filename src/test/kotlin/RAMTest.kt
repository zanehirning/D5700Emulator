import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertFailsWith

class RAMTest {
    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testReadRam() {
        var ram = RAM()
        ram.memory[0] = 15u
        ram.memory[4] = 20u
        ram.memory[3] = UByte.MAX_VALUE
        assert(ram.read(0u).toUInt() == 15u)
        assert(ram.read(4u).toUInt() == 20u)
        assert(ram.read(3u) == UByte.MAX_VALUE)
    }

    @Test
    fun testReadRamOutOfBounds() {
        var ram = RAM()
        assertFailsWith<IndexOutOfBoundsException> {
            ram.read(4000u)
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testWriteRam() {
        var ram = RAM()
        ram.write(0u, 15u)
        assert(ram.memory[0].toUInt() == 15u)

        assertThrows<IndexOutOfBoundsException> {
            ram.write(6000u, 20u)
        }
    }
}