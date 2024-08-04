package org.example

import RAM
import ROM
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class Computer {
    private lateinit var romFile: File

    val rom: ROM = ROM()
    val ram: RAM = RAM()
    val memories = arrayOf(rom, ram) // use the cpu flag to decide which memory to use
    val cpu: CPU = CPU()
    // val instructions (Array of instructions)

    private val executor = Executors.newSingleThreadScheduledExecutor()
    var cpuFuture: ScheduledFuture<*>? = null
    var timerFuture: ScheduledFuture<*>? = null

    init {
        println("Please enter the file you would like to run:")
        val file = "roms/" + readLine()
        try {
            romFile = File(file)
            readRom()
        } catch (e: FileNotFoundException) {
            println("Please provide a rom that exists")
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun readRom() {
        var count = 0
        romFile.forEachLine {
            rom.memory[count] = it.substring(0,2).lowercase().toInt(16).toUByte()
            println(rom.memory[count])
            count ++
            rom.memory[count] = it.substring(2,4).lowercase().toInt(16).toUByte()
            count ++
        }
    }

    fun startCPU() {
        val cpuRunnable = Runnable {
            // TODO: Get the next instruction and run it
        }
        val timerRunnable = Runnable {
            if (cpu.t.toInt() != 0) {
                cpu.t = (cpu.t - 1u).toUByte()
            }
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