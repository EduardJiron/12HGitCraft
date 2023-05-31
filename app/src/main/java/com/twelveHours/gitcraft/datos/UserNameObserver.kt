package com.twelveHours.gitcraft.datos

interface UserNameObserver {
    fun onUserNameChanged(userName: String)
    fun onTokenChanged(token: String)
}
