package com.twelveHours.gitcraft.datos

import com.twelveHours.gitcraft.entidad.Repository

// interface encargada de obtener los repositorios
interface GetRepoListener {
    // funcion que se ejecuta cuando se obtienen los repositorios
    fun onRepoLoaded(repository: List<Repository>)
}