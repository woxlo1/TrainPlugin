package com.woxloi.trainplugin.command

import com.woxloi.trainplugin.TrainPlugin
import com.woxloi.trainplugin.station.StationManager
import com.woxloi.trainplugin.train.Route
import com.woxloi.trainplugin.train.RouteManager
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
            sender.sendMessage("${TrainPlugin.prefix}使い方: /train <start|stop|info|route>")
            return true
        }

        when (args[0].lowercase()) {
            "start" -> {
                if (sender !is Player) {
                    sender.sendMessage("${TrainPlugin.prefix}このコマンドはプレイヤーのみ使用可能です")
                    return true
                }

                val id = args.getOrNull(1) ?: "default"
                val routeName = args.getOrNull(2) ?: "main"

                val route = RouteManager.getRoute(routeName)
                if (route == null || route.size() < 2) {
                    sender.sendMessage("${TrainPlugin.prefix}ルート $routeName が存在しないか、駅が2つ以上必要です")
                    return true
                }

                val train = Train(id, route)
                train.spawn(route.getStationAt(0)!!)
                train.start()
                TrainManager.addTrain(train)

                sender.sendMessage("${TrainPlugin.prefix}電車 $id をルート $routeName で出発させました")
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

            "route" -> {
                val stations = StationManager.listStations().toList()
                if (stations.size < 2) {
                    sender.sendMessage("${TrainPlugin.prefix}駅が2つ以上必要です")
                    return true
                }
                val route = Route("main", stations.toMutableList())
                RouteManager.addRoute(route)
                sender.sendMessage("${TrainPlugin.prefix}ルート main を登録しました (駅数=${stations.size})")
            }

            else -> sender.sendMessage("${TrainPlugin.prefix}不明なサブコマンドです")
        }

        return true
    }
}
