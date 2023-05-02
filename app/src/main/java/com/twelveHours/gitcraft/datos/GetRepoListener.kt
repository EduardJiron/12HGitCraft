package com.twelveHours.gitcraft.datos

import com.twelveHours.gitcraft.entidad.Repository

interface GetRepoListener {
    fun onRepoLoaded(repository: List<Repository>)
}