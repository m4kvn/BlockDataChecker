package com.masahirosaito.spigot.blockdatachecker

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

class BlockDataChecker : JavaPlugin() {
    lateinit var playersFile: File
    lateinit var players: Players

    override fun onEnable() {
        playersFile = File(dataFolder, "players.json")
        players = Players.load(playersFile)

        getCommand("bdc").executor = BDCCommand(this)
        server.pluginManager.registerEvents(BDCListener(this), this)
    }

    override fun onDisable() {
        players.write(playersFile)
    }

    data class Players(val players: MutableMap<UUID, Boolean> = mutableMapOf()) {

        fun put(uuid: UUID, boolean: Boolean = false) = players.put(uuid, boolean)

        fun get(uuid: UUID): Boolean = players[uuid] ?: put(uuid).let { false }

        fun write(file: File) {
            file.writeText(GsonBuilder().setPrettyPrinting().create().toJson(this))
        }

        companion object {

            fun load(file: File): Players {

                if (!file.parentFile.exists()) {
                    file.parentFile.mkdirs()
                }

                var players: Players

                if (!file.exists()) {
                    players = Players()
                    file.createNewFile()
                } else {
                    players = Gson().fromJson(file.readText(), Players::class.java)
                }

                players.write(file)

                return players
            }
        }
    }
}
