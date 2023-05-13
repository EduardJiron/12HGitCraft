package com.twelveHours.gitcraft.datos

import com.twelveHours.gitcraft.entidad.User

interface UserCallback {

        fun onReposReceived(repos: User)
        fun onError(errorMessage: String)


}
