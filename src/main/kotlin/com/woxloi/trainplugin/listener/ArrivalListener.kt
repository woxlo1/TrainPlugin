package com.woxloi.trainplugin.listener

import com.woxloi.trainplugin.TrainPlugin
import com.woxloi.trainplugin.train.Train
import org.bukkit.event.Listener

class ArrivalListener : Listener {

    fun onArrival(train: Train, stationName: String) {
        train.passengers().forEach { player ->
            player.sendMessage(
                "${TrainPlugin.prefix}電車 ${train.id} が駅 $stationName に到着しました"
            )
        }
    }
}
