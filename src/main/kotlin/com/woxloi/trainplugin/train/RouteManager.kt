package com.woxloi.trainplugin.train

object RouteManager {
    private val routes = mutableMapOf<String, Route>()

    fun addRoute(route: Route) {
        routes[route.name] = route
    }

    fun getRoute(name: String): Route? = routes[name]

    fun removeRoute(name: String) {
        routes.remove(name)
    }

    fun listRoutes(): Collection<Route> = routes.values
}
