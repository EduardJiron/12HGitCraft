package com.twelveHours.gitcraft.datos

// interface encargada de obtener los datos del usuario
interface GetUserListener {
    // funcion que se ejecuta cuando se obtienen los datos del usuario
    fun onUserLoaded(name: String, followers: String, following: String)
}