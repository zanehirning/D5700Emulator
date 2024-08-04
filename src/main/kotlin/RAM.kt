import org.example.Memory

class RAM : Memory {
    @OptIn(ExperimentalUnsignedTypes::class)
    val memory = UByteArray(4000)

    override fun read(address: Int) {
        TODO("Not yet implemented")
    }

    override fun write() {
        TODO("Not yet implemented")
    }
}