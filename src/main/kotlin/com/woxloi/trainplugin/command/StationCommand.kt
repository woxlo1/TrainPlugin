package com.woxloi.trainplugin.command

import com.woxloi.trainplugin.TrainPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

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
            "create" -> sender.sendMessage("${TrainPlugin.prefix}駅を作成しました（TODO）")
            "remove" -> sender.sendMessage("${TrainPlugin.prefix}駅を削除しました（TODO）")
            "list" -> sender.sendMessage("${TrainPlugin.prefix}駅一覧を表示します（TODO）")
            else -> sender.sendMessage("${TrainPlugin.prefix}不明なサブコマンドです")
        }

        return true
    }
}
