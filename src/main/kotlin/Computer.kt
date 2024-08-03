package org.example

import RAM
import ROM
import java.io.File
import java.io.FileReader
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class Computer(romFile: File?) {
    private val fileReader = FileReader(romFile)

    val rom: ROM = ROM()
    val ram: RAM = RAM()
    val currentMemory: Memory = rom
    val cpu: CPU = CPU()
    // val instructions (Array of instructions)
    private val executor = Executors.newSingleThreadScheduledExecutor()
    var cpuFuture: ScheduledFuture<*>? = null
    var timerFuture: ScheduledFuture<*>? = null

    // TODO: User FileReader to read files into memory
    private fun readRom() {

    }

    fun startCPU() {
        // TODO: Copy all of the bytes from the file into memory
        val cpuRunnable = Runnable {
            // TODO: Get the next instruction and run it
        }
        val timerRunnable = Runnable {
            // TODO: Handle any timer validation, continue if not 0
        }

        cpuFuture = executor.scheduleAtFixedRate(
            cpuRunnable,
            0,
            1000L / 500L,
            TimeUnit.MILLISECONDS
        )
        timerFuture = executor.scheduleAtFixedRate(
            timerRunnable,
            0,
            1000L,
            TimeUnit.MILLISECONDS
        )
    }

    fun pause() {
        cpuFuture?.cancel(true)
    }

    fun stop() {
        cpuFuture?.cancel(true)
        timerFuture?.cancel(true)
    }
}