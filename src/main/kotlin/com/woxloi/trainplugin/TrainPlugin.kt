package com.woxloi.trainplugin

import com.woxloi.trainplugin.command.TrainCommand
import com.woxloi.trainplugin.command.StationCommand
import com.woxloi.trainplugin.listener.BoardListener
import com.woxloi.trainplugin.listener.ArrivalListener
import org.bukkit.plugin.java.JavaPlugin

class TrainPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: TrainPlugin
            private set

        // チャットで使う接頭辞
        const val prefix = "§c[§7§lTrainPlugin§c]§f"
    }

    override fun onEnable() {
        instance = this

        // デフォルトの config を生成
        saveDefaultConfig()

        // マネージャのロード
        StationManager.load()
        RouteManager.load()

        // コマンド登録（prefix は各コマンド内部で利用可能）
        getCommand("train")?.setExecutor(TrainCommand())
        getCommand("station")?.setExecutor(StationCommand())

        // イベントリスナー登録
        server.pluginManager.registerEvents(BoardListener(), this)
        server.pluginManager.registerEvents(ArrivalListener(), this)

        logger.info("${prefix}有効化完了")
    }

    override fun onDisable() {
        // データ保存
        StationManager.save()
        RouteManager.save()

        logger.info("${prefix}無効化完了")
    }
}
