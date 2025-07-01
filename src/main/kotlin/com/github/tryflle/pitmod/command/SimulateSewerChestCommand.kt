package com.github.tryflle.pitmod.command

import com.github.tryflle.pitmod.listener.module.SewerFinder
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting

class SimulateSewerChestCommand : CommandBase() {

    override fun getCommandName(): String? = "pmsewerchest"

    override fun getCommandUsage(sender: ICommandSender?): String? = "/pmsewerchest <start/end>"

    override fun getRequiredPermissionLevel(): Int = 0

    override fun processCommand(
        sender: ICommandSender?,
        args: Array<out String?>?
    ) {
        if (args?.joinToString()!!.contains("start")) {
            SewerFinder.rescan()

            sender?.addChatMessage(ChatComponentText(
                EnumChatFormatting.BOLD.toString() +
                        EnumChatFormatting.DARK_AQUA.toString() +
                        "SEWERS! " +
                        EnumChatFormatting.RESET.toString() +
                        EnumChatFormatting.GRAY.toString() +
                        "A new " +
                        EnumChatFormatting.RESET.toString() +
                        EnumChatFormatting.YELLOW.toString() +
                        "treasure " +
                        EnumChatFormatting.RESET.toString() +
                        EnumChatFormatting.GRAY.toString() +
                        "spawned somewhere!"

            ))
        } else if (args.joinToString().contains("end")) {
            SewerFinder.reset()

            sender?.addChatMessage(ChatComponentText(
                EnumChatFormatting.BOLD.toString() +
                        EnumChatFormatting.DARK_AQUA.toString() +
                        "SEWERS! " +
                        EnumChatFormatting.RESET.toString() +
                        EnumChatFormatting.YELLOW.toString() +
                        "[" +
                        EnumChatFormatting.RESET.toString() +
                        EnumChatFormatting.RED.toString() +
                        EnumChatFormatting.BOLD.toString() +
                        "71" +
                        EnumChatFormatting.RESET.toString() +
                        EnumChatFormatting.YELLOW.toString() +
                        "] " +
                        EnumChatFormatting.RESET.toString() +
                        EnumChatFormatting.GREEN.toString() +
                        "Placeholder " +
                        EnumChatFormatting.RESET.toString() +
                        EnumChatFormatting.GRAY.toString() +
                        "found the treasure!"
            ))
        } else {
            sender?.addChatMessage(ChatComponentText("Invalid argument. Use 'start' or 'end'."))
        }
    }
}