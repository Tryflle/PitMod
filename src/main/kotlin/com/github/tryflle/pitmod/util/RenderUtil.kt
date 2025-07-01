package com.github.tryflle.pitmod.util

import net.minecraft.client.Minecraft
import org.lwjgl.opengl.GL11.*


object RenderUtil {

    fun draw3DBox(x1: Double, y1: Double, z1: Double, red: Float, green: Float, blue: Float, alpha: Float, height: Double) {
        val x = x1 - Minecraft.getMinecraft().getRenderManager().viewerPosX
        val y = y1 + 0.001 - Minecraft.getMinecraft().getRenderManager().viewerPosY
        val z = z1 - Minecraft.getMinecraft().getRenderManager().viewerPosZ

        glPushMatrix()
        glTranslated(x, y, z)
        glDisable(GL_TEXTURE_2D)
        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
        glColor4f(red, green, blue, alpha)
        glDisable(GL_CULL_FACE)
        glBegin(GL_QUADS)

        glVertex3d(-0.5, 0.0, -0.5)
        glVertex3d(0.5, 0.0, -0.5)
        glVertex3d(0.5, 0.0, 0.5)
        glVertex3d(-0.5, 0.0, 0.5)

        glVertex3d(-0.5, height, -0.5)
        glVertex3d(0.5, height, -0.5)
        glVertex3d(0.5, height, 0.5)
        glVertex3d(-0.5, height, 0.5)

        glVertex3d(-0.5, 0.0, -0.5)
        glVertex3d(-0.5, height, -0.5)
        glVertex3d(0.5, height, -0.5)
        glVertex3d(0.5, 0.0, -0.5)

        glVertex3d(-0.5, 0.0, 0.5)
        glVertex3d(-0.5, height, 0.5)
        glVertex3d(0.5, height, 0.5)
        glVertex3d(0.5, 0.0, 0.5)

        glVertex3d(-0.5, 0.0, -0.5)
        glVertex3d(-0.5, height, -0.5)
        glVertex3d(-0.5, height, 0.5)
        glVertex3d(-0.5, 0.0, 0.5)

        glVertex3d(0.5, 0.0, -0.5)
        glVertex3d(0.5, height, -0.5)
        glVertex3d(0.5, height, 0.5)
        glVertex3d(0.5, 0.0, 0.5)

        glEnd()
        glEnable(GL_TEXTURE_2D)
        glDisable(GL_BLEND)
        glPopMatrix()
        glEnable(GL_CULL_FACE)
    }

    fun draw3DWireframeBox(x1: Double, y1: Double, z1: Double, red: Float, green: Float, blue: Float, alpha: Float, height: Double) {
        val x = x1 - Minecraft.getMinecraft().getRenderManager().viewerPosX
        val y = y1 + 0.001 - Minecraft.getMinecraft().getRenderManager().viewerPosY
        val z = z1 - Minecraft.getMinecraft().getRenderManager().viewerPosZ

        glPushMatrix()
        glTranslated(x, y, z)
        glDisable(GL_TEXTURE_2D)
        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
        glColor4f(red, green, blue, alpha)
        glDisable(GL_CULL_FACE)
        glBegin(GL_LINES)

        glVertex3d(-0.5, 0.0, -0.5)
        glVertex3d(0.5, 0.0, -0.5)

        glVertex3d(0.5, 0.0, -0.5)
        glVertex3d(0.5, 0.0, 0.5)

        glVertex3d(0.5, 0.0, 0.5)
        glVertex3d(-0.5, 0.0, 0.5)

        glVertex3d(-0.5, 0.0, 0.5)
        glVertex3d(-0.5, 0.0, -0.5)

        // Top face
        glVertex3d(-0.5, height, -0.5)
        glVertex3d(0.5, height, -0.5)

        glVertex3d(0.5, height, -0.5)
        glVertex3d(0.5, height, 0.5)

        glVertex3d(0.5, height, 0.5)
        glVertex3d(-0.5, height, 0.5)

        glVertex3d(-0.5, height, 0.5)
        glVertex3d(-0.5, height, -0.5)

        // Vertical edges
        glVertex3d(-0.5, 0.0, -0.5)
        glVertex3d(-0.5, height, -0.5)

        glVertex3d(0.5, 0.0, -0.5)
        glVertex3d(0.5, height, -0.5)

        glVertex3d(0.5, 0.0, 0.5)
        glVertex3d(0.5, height, 0.5)

        glVertex3d(-0.5, 0.0, 0.5)
        glVertex3d(-0.5, height, 0.5)

        glEnd()
        glEnable(GL_TEXTURE_2D)
        glDisable(GL_BLEND)
        glPopMatrix()
        glEnable(GL_CULL_FACE)
    }
}