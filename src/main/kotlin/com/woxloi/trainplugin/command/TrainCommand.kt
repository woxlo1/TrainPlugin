package com.woxloi.trainplugin.command

import com.woxloi.trainplugin.TrainPlugin
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
            sender.sendMessage("${TrainPlugin.prefix}使い方: /train <start|stop|info|list> <trainID>")
            return true
        }

        val sub = args[0].lowercase()
        val trainId = args.getOrNull(1)

        when (sub) {
            "start" -> {
                if (trainId == null) {
                    sender.sendMessage("${TrainPlugin.prefix}trainID を指定してください")
                } else if (TrainManager.startTrain(trainId)) {
                    sender.sendMessage("${TrainPlugin.prefix}電車 $trainId を出発させました")
                } else {
                    sender.sendMessage("${TrainPlugin.prefix}電車 $trainId は存在しません")
                }
            }

            "stop" -> {
                if (trainId == null) {
                    sender.sendMessage("${TrainPlugin.prefix}trainID を指定してください")
                } else if (TrainManager.stopTrain(trainId)) {
                    sender.sendMessage("${TrainPlugin.prefix}電車 $trainId を停止させました")
                } else {
                    sender.sendMessage("${TrainPlugin.prefix}電車 $trainId は存在しません")
                }
            }

            "info" -> {
                if (trainId == null) {
                    sender.sendMessage("${TrainPlugin.prefix}trainID を指定してください")
                } else {
                    val train = TrainManager.getTrain(trainId)
                    if (train != null) {
                        sender.sendMessage("${TrainPlugin.prefix}電車 $trainId の状態: ${if (train.isRunning) "走行中" else "停止中"}")
                    } else {
                        sender.sendMessage("${TrainPlugin.prefix}電車 $trainId は存在しません")
                    }
                }
            }

            "list" -> {
                val trains = TrainManager.listTrains()
                if (trains.isEmpty()) {
                    sender.sendMessage("${TrainPlugin.prefix}登録されている電車はありません")
                } else {
                    sender.sendMessage("${TrainPlugin.prefix}電車一覧:")
                    trains.forEach { sender.sendMessage("- ${it.id}: ${if (it.isRunning) "走行中" else "停止中"}") }
                }
            }

            else -> sender.sendMessage("${TrainPlugin.prefix}不明なサブコマンドです")
        }

        return true
    }
}
