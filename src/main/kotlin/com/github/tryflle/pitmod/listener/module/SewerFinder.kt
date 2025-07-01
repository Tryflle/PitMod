package com.github.tryflle.pitmod.listener.module

import com.github.tryflle.pitmod.listener.api.PitModListener
import com.github.tryflle.pitmod.util.RenderUtil
import net.minecraft.tileentity.TileEntityChest
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.opengl.GL11
import javax.vecmath.Vector3f

object SewerFinder : PitModListener<SewerFinder>() {

    private var chestCoords: Vector3f = Vector3f(0f, 0f, 0f)

    private var tick: Int = 0

    private var rescanScheduled: Boolean = false
    private var chestFound: Boolean = false
    private var chestExists: Boolean = false

    @SubscribeEvent
    fun onRenderWorldLast(e: RenderWorldLastEvent) {
        if (mc().theWorld != null && chestCoords != Vector3f(0f, 0f, 0f)) {

            GL11.glDisable(GL11.GL_DEPTH_TEST)

            RenderUtil.draw3DBox(chestCoords.x.toDouble()+0.5, chestCoords.y.toDouble(), chestCoords.z.toDouble()+0.5, 0.5f, 0.1f, 0.5f, 0.25f, 1.0)
            RenderUtil.draw3DWireframeBox(chestCoords.x.toDouble()+0.5, chestCoords.y.toDouble(), chestCoords.z.toDouble()+0.5, 0.0883f, 0.5176f, 1.0f, 1.0f, 1.0)

            GL11.glEnable(GL11.GL_DEPTH_TEST)
        }
    }

    @SubscribeEvent
    fun onRenderTick(e: TickEvent.RenderTickEvent) {
        if (mc().theWorld == null || mc().thePlayer == null) return
        if (chestFound) {

            mc().fontRendererObj.drawStringWithShadow(
                "Sewer Chest Found at: ${chestCoords.x.toInt()}, ${chestCoords.y.toInt()}, ${chestCoords.z.toInt()}",
                2f, 2f, 0x0883ff
            )
        }
        else {

            mc().fontRendererObj.drawStringWithShadow(
                "Sewer Chest Not Found",
                2f, 2f, 0xde4e85
            )
        }
    }

    @SubscribeEvent
    fun onTick(e: TickEvent.ClientTickEvent) {
        tick++

        if (tick % 40 == 0) {
            if (rescanScheduled || (chestExists && !chestFound)) {
                rescanScheduled = false
                chestCoords = getChestCooords()
                if (chestCoords != Vector3f(0f, 0f, 0f)) {
                    chestFound = true
                } else {
                    chestFound = false
                }
            }
        }
    }

    @SubscribeEvent
    fun onChatReceived(e: ClientChatReceivedEvent) {
        val message = e.message.unformattedText.toString()

        if (message == "SEWERS! A new treasure spawned somewhere!") {
            rescan()
        }
        if (message.startsWith("SEWERS!") && message.endsWith("found the treasure!")) {
            reset()
        }
    }

    private fun getChestCooords(): Vector3f {
        val player = mc().thePlayer
        val world = mc().theWorld

        if (player == null || world == null) {
            return Vector3f(0f, 0f, 0f)
        }

        val dimension = player.dimension
        val playerPos = player.positionVector

        val chests = world.loadedTileEntityList
            .filterIsInstance<TileEntityChest>()
            .filter { it.world.provider.dimensionId == dimension }

        val nearestChest = chests.minByOrNull { chest ->
            chest.pos.distanceSq(
                playerPos.xCoord,
                playerPos.yCoord,
                playerPos.zCoord
            )
        }

        return if (nearestChest != null) {
            Vector3f(
                nearestChest.pos.x.toFloat(),
                nearestChest.pos.y.toFloat(),
                nearestChest.pos.z.toFloat()
            ).also { chestCoords = it }
        } else {
            Vector3f(0f, 0f, 0f)
        }
    }


    fun rescan() {
        chestExists = true
        rescanScheduled = true
    }

    fun reset() {
        chestExists = false
        chestFound = false
        chestCoords = Vector3f(0f, 0f, 0f)
    }
}