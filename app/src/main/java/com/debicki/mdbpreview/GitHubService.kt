package com.debicki.mdbpreview

import retrofit2.http.GET

data class Comment(private val name: String)

interface GitHubService {
    @GET("comments")
    suspend fun listRepos(): List<Comment>
}