package com.masahirosaito.spigot.blockdatachecker

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class BDCListener(val plugin: BlockDataChecker) : Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.isCancelled) return
        if (event.action != Action.RIGHT_CLICK_BLOCK) return
        if (!plugin.players.get(event.player.uniqueId)) return

        event.player.sendMessage("Block: ${event.clickedBlock.state.data}")
        plugin.players.put(event.player.uniqueId, false)
    }
}