package com.woxloi.trainplugin.train

import com.woxloi.trainplugin.station.Station

data class Route(
    val name: String,
    private val stations: MutableList<Station> = mutableListOf()
) {

    fun addStation(station: Station) {
        stations.add(station)
    }

    fun removeStation(station: Station) {
        stations.remove(station)
    }

    fun getStations(): List<Station> = stations.toList()

    fun size(): Int = stations.size

    fun getStationAt(index: Int): Station? =
        if (index in stations.indices) stations[index] else null
}
