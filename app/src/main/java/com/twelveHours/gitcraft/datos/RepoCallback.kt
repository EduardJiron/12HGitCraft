package com.twelveHours.gitcraft.datos

import com.twelveHours.gitcraft.entidad.Repository

interface RepoCallback {
    fun onReposReceived(repos: List<Repository>, number: Int)
    fun onError(errorMessage: String)

}