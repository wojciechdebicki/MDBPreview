package com.debicki.mdbpreview

import javax.inject.Inject

class CommentRepository @Inject constructor(private val gitHubService: GitHubService) {

    suspend fun fetchComments() = gitHubService.listRepos()
}