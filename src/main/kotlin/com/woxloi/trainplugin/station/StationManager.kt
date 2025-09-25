package com.woxloi.trainplugin.station

import com.woxloi.trainplugin.TrainPlugin
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object StationManager {

    private val stations = mutableMapOf<String, Station>()
    private val file: File = File(TrainPlugin.instance.dataFolder, "stations.yml")
    private lateinit var yaml: YamlConfiguration

    fun load() {
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }
        yaml = YamlConfiguration.loadConfiguration(file)

        yaml.getKeys(false).forEach { id ->
            val section = yaml.getConfigurationSection(id) ?: return@forEach
            val station = Station(
                id = id,
                name = section.getString("name") ?: id,
                world = section.getString("world") ?: "world",
                x = section.getDouble("x"),
                y = section.getDouble("y"),
                z = section.getDouble("z"),
                home = section.getBoolean("home", false)
            )
            stations[id] = station
        }
    }

    fun save() {
        stations.forEach { (id, station) ->
            val section = yaml.createSection(id)
            section["name"] = station.name
            section["world"] = station.world
            section["x"] = station.x
            section["y"] = station.y
            section["z"] = station.z
            section["home"] = station.home
        }
        yaml.save(file)
    }

    fun addStation(station: Station) {
        stations[station.id] = station
        save()
    }

    fun removeStation(id: String): Boolean {
        val removed = stations.remove(id) != null
        if (removed) save()
        return removed
    }

    fun listStations(): Collection<Station> = stations.values

    fun getStation(id: String): Station? = stations[id]
}
