package com.twelveHours.gitcraft.datos

interface GetUserListener {
    fun onUserLoaded(name: String, followers: String, following: String)
}