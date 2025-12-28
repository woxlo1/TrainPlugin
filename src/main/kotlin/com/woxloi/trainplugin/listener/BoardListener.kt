package com.woxloi.trainplugin.listener

import com.woxloi.trainplugin.TrainPlugin
import com.woxloi.trainplugin.train.TrainManager
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent

class BoardListener : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractAtEntityEvent) {
        val player: Player = event.player
        val entity = event.rightClicked

        if (entity is ArmorStand && entity.customName?.startsWith("Train-") == true) {
            val trainId = entity.customName!!.removePrefix("Train-")
            val train = TrainManager.getTrain(trainId)
            if (train != null) {
                //train.addPassenger(player) // Train.kt に addPassenger を用意する
                player.sendMessage("${TrainPlugin.prefix}電車 $trainId に乗車しました")
            }
        }
    }
}
