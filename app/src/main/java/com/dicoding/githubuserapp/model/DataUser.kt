package com.dicoding.githubuserapp.model

data class DataUser(
        var avatar: String? = null,
        var name: String? = null,
        var id: Int = 0,
        var username: String? = null,
        var location: String? = null,
        var company: String? = null,
        var repos: String? = null,
        var followers: String? = null,
        var following: String? = null
)