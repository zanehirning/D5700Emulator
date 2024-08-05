class Screen {
    @OptIn(ExperimentalUnsignedTypes::class)
    var memory = UByteArray(64)

    @OptIn(ExperimentalUnsignedTypes::class)
    fun draw() {
        for (i in 0 until 8) {
            for (j in 0 until 8) {
                print(memory[i * 8 + j].toInt().toChar())
            }
            println()
        }
        println("========")
    }
}