package com.woxloi.trainplugin.command

import com.woxloi.trainplugin.TrainPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

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
            "start" -> sender.sendMessage("${TrainPlugin.prefix}電車を出発させます（TODO）")
            "stop" -> sender.sendMessage("${TrainPlugin.prefix}電車を停止させます（TODO）")
            "info" -> sender.sendMessage("${TrainPlugin.prefix}電車情報を表示します（TODO）")
            else -> sender.sendMessage("${TrainPlugin.prefix}不明なサブコマンドです")
        }

        return true
    }
}
