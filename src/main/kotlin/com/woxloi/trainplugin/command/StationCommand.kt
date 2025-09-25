package com.woxloi.trainplugin.command

import com.woxloi.trainplugin.TrainPlugin
import com.woxloi.trainplugin.station.Station
import com.woxloi.trainplugin.station.StationManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class StationCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("${TrainPlugin.prefix}使い方: /station <create|remove|list>")
            return true
        }

        when (args[0].lowercase()) {
            "create" -> {
                if (sender !is Player) {
                    sender.sendMessage("${TrainPlugin.prefix}このコマンドはプレイヤーのみ使用可能です")
                    return true
                }
                val id = args.getOrNull(1) ?: sender.location.blockX.toString() + sender.location.blockZ.toString()
                val name = args.getOrNull(2) ?: id
                val station = Station(
                    id = id,
                    name = name,
                    world = sender.world.name,
                    x = sender.location.x,
                    y = sender.location.y,
                    z = sender.location.z
                )
                StationManager.addStation(station)
                sender.sendMessage("${TrainPlugin.prefix}駅「${station.name}」を作成しました")
            }

            "remove" -> {
                val id = args.getOrNull(1)
                if (id == null || !StationManager.removeStation(id)) {
                    sender.sendMessage("${TrainPlugin.prefix}駅ID「$id」が見つかりません")
                } else {
                    sender.sendMessage("${TrainPlugin.prefix}駅「$id」を削除しました")
                }
            }

            "list" -> {
                val list = StationManager.listStations()
                if (list.isEmpty()) {
                    sender.sendMessage("${TrainPlugin.prefix}駅は登録されていません")
                } else {
                    sender.sendMessage("${TrainPlugin.prefix}駅一覧:")
                    list.forEach { sender.sendMessage("- ${it.id}: ${it.name}") }
                }
            }

            else -> sender.sendMessage("${TrainPlugin.prefix}不明なサブコマンドです")
        }

        return true
    }
}
