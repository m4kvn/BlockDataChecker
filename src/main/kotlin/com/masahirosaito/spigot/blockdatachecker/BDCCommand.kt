package com.masahirosaito.spigot.blockdatachecker

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BDCCommand(val plugin: BlockDataChecker) : CommandExecutor {

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {

        if (sender is Player) plugin.players.apply { put(sender.uniqueId, true) }

        return true
    }
}