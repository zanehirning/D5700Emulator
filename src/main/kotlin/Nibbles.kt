class Nibbles(val b0: UByte, val b1: UByte) {
    fun getFirstNibble() = (b0.toInt() shr 4).toUByte()
    fun getSecondNibble() = (b0.toInt() and 0xF).toUByte()
    fun getThirdNibble() = (b1.toInt() shr 4).toUByte()
    fun getFourthNibble() = (b1.toInt() and 0xF).toUByte()
    fun combineNibbles(nibble0: UByte, nibble1: UByte) = (nibble0.toInt() shl 4) or nibble1.toInt()
    fun getAddress(nibble0: UByte, nibble1: UByte, nibble2: UByte): UShort {
        val n0 = nibble0.toInt() shl 8
        val n1 = nibble1.toInt() shl 4
        val n2 = nibble2.toInt()
        return (n0 + n1 + n2).toUShort()
    }
}