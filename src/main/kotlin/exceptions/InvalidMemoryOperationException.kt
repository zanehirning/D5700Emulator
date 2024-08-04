package exceptions

class InvalidMemoryOperationException(val suppliedMessage: String?) : Exception() {
    override val message: String?
        get() = suppliedMessage ?: "Invalid memory operation"
}