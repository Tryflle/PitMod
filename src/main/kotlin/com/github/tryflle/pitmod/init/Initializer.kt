package com.github.tryflle.pitmod.init

import com.github.tryflle.pitmod.listener.module.SewerFinder
import net.minecraftforge.common.MinecraftForge

object Initializer {

    var initialized = false

    fun deinitialize() {
        if (!initialized) return
        initialized = false
        println("Deinitializing PitMod")

        MinecraftForge.EVENT_BUS.unregister(SewerFinder)
    }

    fun initialize() {
        if (initialized) return
        initialized = true
        println("Initializing PitMod")

        MinecraftForge.EVENT_BUS.register(SewerFinder)
    }
}