import org.example.Memory

class RAM : Memory() {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun write(address: UShort, value: UByte) {
        memory[address.toInt()] = value
    }
}