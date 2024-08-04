import exceptions.InvalidMemoryOperationException
import org.example.Memory

class ROM : Memory() {
    override fun write(address: UShort, value: UByte) {
        throw InvalidMemoryOperationException("Read only memory cannot write")
    }
}