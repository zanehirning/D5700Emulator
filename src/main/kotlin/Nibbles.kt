class Nibbles(val b0: UByte, val b1: UByte) {
    fun getFirstNibble() = (b0.toInt() shr 4).toUByte()
    fun getSecondNibble() = (b0.toInt() and 0xF).toUByte()
    fun getThirdNibble() = (b1.toInt() shr 4).toUByte()
    fun getFourthNibble() = (b1.toInt() and 0xF).toUByte()
    fun combineNibbles(nibble0: UByte, nibble1: UByte) = (nibble0.toInt() shl 4) or nibble1.toInt()
}