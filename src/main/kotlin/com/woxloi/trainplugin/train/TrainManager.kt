package com.woxloi.trainplugin.train

object TrainManager {
    private val trains = mutableMapOf<String, Train>()

    fun addTrain(train: Train) {
        trains[train.id] = train
    }

    fun getTrain(id: String): Train? = trains[id]

    fun removeTrain(id: String) {
        trains.remove(id)?.stop()
    }

    fun listTrains(): Collection<Train> = trains.values
}
