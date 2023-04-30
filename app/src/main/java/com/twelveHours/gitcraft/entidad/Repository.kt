package com.twelveHours.gitcraft.entidad

import java.net.URL

data class Repository
    (val name: String,
     val description: String?,
     val stargazers_count: Int?,
     val url: URL?)
