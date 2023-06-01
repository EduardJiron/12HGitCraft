package com.twelveHours.gitcraft.negocio

import com.twelveHours.gitcraft.datos.UserNameObserver


object UserName {
    private var userName: String = ""
    private var token: String = ""
    private var id: Int = 0

    fun getUserName(): String {
        return userName
    }

    fun setUserName(userName: String) {
        this.userName = userName
    }

    fun getToken(): String {
        return token
    }

    fun setToken(token: String) {
        this.token = token
    }

    fun getUserId(): Int{
        return id
    }

    fun setId(id : Int){
        this.id = id
    }
}




