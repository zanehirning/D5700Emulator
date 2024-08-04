import org.example.Memory

class ROM : Memory() {
    override fun write() {
        throw Exception("Read only memory cannot write")
    }
}