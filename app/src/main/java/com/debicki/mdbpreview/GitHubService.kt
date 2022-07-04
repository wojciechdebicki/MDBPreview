package com.debicki.mdbpreview

import retrofit2.Call
import retrofit2.http.GET

data class Comment(private val name: String)

interface GitHubService {
    @GET("comments")
    fun listRepos(): Call<List<Comment>>
}