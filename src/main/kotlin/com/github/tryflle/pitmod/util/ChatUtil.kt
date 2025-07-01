package com.github.tryflle.pitmod.util

object ChatUtil {

    fun stripEnumChatFormatting(text: String): String {
        return text.replace(Regex("§[0-9a-fk-or]"), "")
    }
}