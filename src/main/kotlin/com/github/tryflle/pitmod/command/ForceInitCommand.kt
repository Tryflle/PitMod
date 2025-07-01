package com.github.tryflle.pitmod.command

import com.github.tryflle.pitmod.init.Initializer
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting

class ForceInitCommand : CommandBase() {

    override fun getCommandName(): String? = "pmforceinit"

    override fun getCommandUsage(sender: ICommandSender?): String? = "/pmforceinit"

    override fun getRequiredPermissionLevel(): Int = 0

    override fun processCommand(
        sender: ICommandSender?,
        args: Array<out String?>?
    ) {
        if (sender != null) {
            val state = Initializer.initialized

            if (state) {
                Initializer.deinitialize()
            } else {
                Initializer.initialize()
            }
            sender.addChatMessage(
                ChatComponentText(
                    EnumChatFormatting.LIGHT_PURPLE.toString() +
                "PitMod " + if (!state) "initialized" else "deinitialized")
            )
        }
    }
}