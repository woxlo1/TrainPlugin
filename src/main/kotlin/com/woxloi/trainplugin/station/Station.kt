package com.woxloi.trainplugin.station

import org.bukkit.Location

data class Station(
    val id: String,
    val name: String,
    val world: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val home: Boolean = false
) {
    fun toLocation(): Location {
        return Location(
            org.bukkit.Bukkit.getWorld(world),
            x, y, z
        )
    }
}
