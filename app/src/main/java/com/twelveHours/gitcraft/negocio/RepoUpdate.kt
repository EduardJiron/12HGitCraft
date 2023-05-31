package com.twelveHours.gitcraft.negocio

object RepoUpdate {
    private var RepoName: String = ""


    fun getUserName(): String {
        return RepoName
    }

    fun setUserName(RepoName: String) {
        this.RepoName = RepoName
    }


}
