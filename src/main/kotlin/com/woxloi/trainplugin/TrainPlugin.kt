package com.woxloi.trainplugin


import org.bukkit.plugin.java.JavaPlugin


class TrainPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: TrainPlugin
            private set
    }

    override fun onEnable() {
        instance = this
        saveDefaultConfig()

        // ローダー / マネージャ初期化
        StationManager.load()
        RouteManager.load()

        // コマンド登録
        getCommand("train")?.setExecutor(TrainCommand())
        getCommand("station")?.setExecutor(StationCommand())

        // リスナー登録
        server.pluginManager.registerEvents(BoardListener(), this)
        server.pluginManager.registerEvents(ArrivalListener(), this)


        logger.info("TrainPlugin enabled")
    }

    override fun onDisable() {
        StationManager.save()
        RouteManager.save()
        logger.info("TrainPlugin disabled")
    }
}