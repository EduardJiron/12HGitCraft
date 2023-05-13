package com.twelveHours.gitcraft.entidad

import com.google.gson.annotations.SerializedName


data class User(
    val User: String,
    @SerializedName("avatar_url")   val image: String,
    val login: String,
    val followers: String,
    val following: String,
    val email: String,

)
