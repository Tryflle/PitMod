package com.github.tryflle.pitmod.listener.api


import net.minecraft.client.Minecraft
import net.minecraftforge.common.MinecraftForge

open class PitModListener<T : PitModListener<T>> {

    fun mc(): Minecraft {
        if (Minecraft.getMinecraft() == null) {
            throw IllegalStateException("Minecraft instance is not initialized.")
        }
        return Minecraft.getMinecraft()
    }

    val instance: T
        get() = this as T

    open fun onEnable() {
    }

    open fun onDisable() {
    }

    fun disable() {
        onDisable()
        MinecraftForge.EVENT_BUS.unregister(instance)
    }
}