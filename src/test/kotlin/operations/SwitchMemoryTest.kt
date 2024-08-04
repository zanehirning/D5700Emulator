package operations

import org.example.Computer
import kotlin.test.Test

class SwitchMemoryTest {
    @Test
    fun testPerformOperation() {
        val switchMemory = SwitchMemory()
        val computer = Computer()
        switchMemory.performOperation(computer)
        assert(computer.cpu.m == 1)
        switchMemory.performOperation(computer)
        assert(computer.cpu.m == 0)
    }
}