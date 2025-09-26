package com.woxloi.trainplugin.train

import com.woxloi.trainplugin.station.Station
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.ArmorStand

class Train(
    val id: String,
    val route: Route,
    private var index: Int = 0
) {
    private var entity: ArmorStand? = null
    private var running = false

    fun spawn(startStation: Station) {
        val world = Bukkit.getWorld(startStation.world) ?: return
        val loc = Location(world, startStation.x, startStation.y, startStation.z)

        entity = world.spawn(loc, ArmorStand::class.java).apply {
            isInvisible = true
            isMarker = true
            customName = "Train-$id"
            isCustomNameVisible = true
        }
    }

    fun start() {
        if (running) return
        running = true
        moveToNextStation()
    }

    private fun moveToNextStation() {
        if (!running || entity == null) return

        val next = route.stations[(index + 1) % route.stations.size]
        val world = Bukkit.getWorld(next.world) ?: return
        val target = Location(world, next.x, next.y, next.z)

        val steps = 40 // 2秒くらいで到着
        val current = entity!!.location.clone()
        val dx = (target.x - current.x) / steps
        val dy = (target.y - current.y) / steps
        val dz = (target.z - current.z) / steps

        var count = 0
        val task = Bukkit.getScheduler().runTaskTimer(
            Bukkit.getPluginManager().getPlugin("TrainPlugin")!!,
            Runnable {
                if (!running || entity == null) return@Runnable
                if (count >= steps) {
                    entity!!.teleport(target)
                    index = (index + 1) % route.stations.size
                    Bukkit.getLogger().info("Train $id が駅 ${next.name} に到着しました")

                    // 停車後に次の駅へ
                    Bukkit.getScheduler().runTaskLater(
                        Bukkit.getPluginManager().getPlugin("TrainPlugin")!!,
                        Runnable { if (running) moveToNextStation() },
                        40L // 2秒停車
                    )
                    task.cancel()
                    return@Runnable
                }
                val loc = entity!!.location.clone().add(dx, dy, dz)
                entity!!.teleport(loc)
                count++
            },
            0L, 1L
        )
    }

    fun stop() {
        running = false
    }

    fun info(): String {
        val current = route.stations[index]
        return "Train-$id: 現在地=${current.name}, 停止中=${!running}"
    }
}
