package com.twelveHours.gitcraft.entidad

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("avatar_url")   val image: String,
    val login: String,
    val public_repos: String,
    val followers: String,
    val following: String,
)




