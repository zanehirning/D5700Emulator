package org.example

import Nibbles
import RAM
import ROM
import Screen
import operations.*
import java.io.File
import java.io.FileNotFoundException
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

class Computer {
    private lateinit var romFile: File

    val rom: ROM = ROM()
    val ram: RAM = RAM()
    val memories = arrayOf(ram, rom)
    val cpu: CPU = CPU()
    val instructions = arrayOf(
        Store(),
        Add(),
        Sub(),
        Read(),
        Write(),
        Jump(),
        ReadKeyboard(),
        SwitchMemory(),
        SkipEqual(),
        SkipNotEqual(),
        SetA(),
        SetT(),
        ReadT(),
        ConvertToBaseTen(),
        ConvertByteToAscii(),
        Draw()
    )
    val screen: Screen = Screen()
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var cpuFuture: ScheduledFuture<*>? = null
    private var timerFuture: ScheduledFuture<*>? = null

    init {
        println("Please enter the file you would like to run:")
        val file = "roms/" + readLine()
        try {
            romFile = File(file)
            readRom()
            startCPU()
        } catch (e: FileNotFoundException) {
            println("Please provide a rom that exists")
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun readRom() {
        romFile.readBytes().toUByteArray().copyInto(rom.memory)
    }

    fun startCPU() {
        val cpuRunnable = Runnable {
            try {
                val byte1 = rom.read(cpu.p)
                val byte2 = rom.read((cpu.p + 1u).toUShort())
                if (byte1.toInt() == 0 && byte2.toInt() == 0) {
                    stop()
                    return@Runnable
                }
                val nibbles = Nibbles(byte1, byte2)
                instructions[nibbles.getFirstNibble().toInt()].execute(this, nibbles)
            } catch (e: Exception) {
                e.printStackTrace()
                stop()
            }
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
            1000/60L,
            TimeUnit.MILLISECONDS
        )
    }

    fun pause() {
        cpuFuture?.cancel(true)
    }

    fun stop() {
        cpuFuture?.cancel(true)
        timerFuture?.cancel(true)
        exitProcess(0)
    }
}