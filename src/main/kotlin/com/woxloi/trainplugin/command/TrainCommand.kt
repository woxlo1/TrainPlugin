package com.woxloi.trainplugin.command

import com.woxloi.trainplugin.TrainPlugin
import com.woxloi.trainplugin.station.StationManager
import com.woxloi.trainplugin.train.Route
import com.woxloi.trainplugin.train.Train
import com.woxloi.trainplugin.train.TrainManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TrainCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("${TrainPlugin.prefix}使い方: /train <start|stop|info>")
            return true
        }

        when (args[0].lowercase()) {
            "start" -> {
                if (sender !is Player) {
                    sender.sendMessage("${TrainPlugin.prefix}このコマンドはプレイヤーのみ使用可能です")
                    return true
                }

                val id = args.getOrNull(1) ?: "default"
                val stations = StationManager.listStations()
                if (stations.size < 2) {
                    sender.sendMessage("${TrainPlugin.prefix}駅が2つ以上必要です")
                    return true
                }

                val route = Route(stations.toList())
                val train = Train(id, route)
                train.spawn(stations.first())
                train.start()
                TrainManager.addTrain(train)

                sender.sendMessage("${TrainPlugin.prefix}電車 $id を出発させました")
            }

            "stop" -> {
                val id = args.getOrNull(1) ?: "default"
                TrainManager.getTrain(id)?.stop()
                sender.sendMessage("${TrainPlugin.prefix}電車 $id を停止しました")
            }

            "info" -> {
                TrainManager.listTrains().forEach {
                    sender.sendMessage("${TrainPlugin.prefix}${it.info()}")
                }
            }

            else -> sender.sendMessage("${TrainPlugin.prefix}不明なサブコマンドです")
        }

        return true
    }
}
