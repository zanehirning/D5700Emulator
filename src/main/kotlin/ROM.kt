import org.example.Memory

class ROM : Memory {

    val memory = ByteArray(4000)

    override fun read() {
        TODO("Not yet implemented")
    }

    override fun write() {
        throw Exception("Read only memory cannot write")
    }
}