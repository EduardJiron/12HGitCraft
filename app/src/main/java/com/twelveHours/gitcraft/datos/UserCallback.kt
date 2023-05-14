package com.twelveHours.gitcraft.datos

import com.twelveHours.gitcraft.entidad.User

interface UserCallback {

    fun onUserReceived(user: User)

    fun onError(errorMessage: String)
}
