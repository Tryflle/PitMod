package com.github.tryflle.pitmod

import com.github.tryflle.pitmod.command.ForceInitCommand
import com.github.tryflle.pitmod.command.SimulateSewerChestCommand
import com.github.tryflle.pitmod.init.Initializer
import net.minecraft.client.Minecraft
import net.minecraft.init.Blocks
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.command.CommandHandler
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.client.MinecraftForgeClient
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.EventBus

@Mod(modid = "pitmod", useMetadata = true)
class PitMod {

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        Initializer.initialize()

        ClientCommandHandler.instance.registerCommand(ForceInitCommand())
        ClientCommandHandler.instance.registerCommand(SimulateSewerChestCommand())
    }
}
