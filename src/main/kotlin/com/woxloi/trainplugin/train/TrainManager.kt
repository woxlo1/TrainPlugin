package com.woxloi.trainplugin.train

object TrainManager {
    private val trains = mutableMapOf<String, Train>()

    fun addTrain(train: Train) {
        trains[train.id] = train
    }

    fun getTrain(id: String): Train? = trains[id]

    fun startTrain(id: String): Boolean {
        val train = trains[id] ?: return false
        train.isRunning = true
        return true
    }

    fun stopTrain(id: String): Boolean {
        val train = trains[id] ?: return false
        train.isRunning = false
        return true
    }

    fun listTrains(): List<Train> = trains.values.toList()
}
