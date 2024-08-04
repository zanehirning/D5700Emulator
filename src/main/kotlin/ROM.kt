import org.example.Memory

class ROM : Memory {
    @OptIn(ExperimentalUnsignedTypes::class)
    val memory = UByteArray(4000)

    override fun read(address: Int) {
        TODO("Not yet implemented")
    }

    override fun write() {
        throw Exception("Read only memory cannot write")
    }
}