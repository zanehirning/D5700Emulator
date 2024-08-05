import org.example.Memory

class RAM : Memory() {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun write(address: UShort, value: UByte) {
        if (address.toInt() >= memory.size) {
            throw IndexOutOfBoundsException("Attempted to write to out of bounds memory address")
        }
        memory[address.toInt()] = value
    }
}