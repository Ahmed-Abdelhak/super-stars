package com.redcare.pharmacy.common.model

import com.google.gson.annotations.SerializedName

data class GithubRepositoryModel(
    override val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val description: String?,
    val language: String?,
    val owner: GithubOwnerModel,
//    val topics: List<String>,
//    val stargazersCount: Int,
//    val isPrivate: Boolean,
//    val visibility: String,
//    @SerializedName("archived")
//    val isArchived: Boolean,
//    @SerializedName("disabled")
//    val isDisabled: Boolean,
//    @SerializedName("has_issues")
//    val hasIssues: Boolean,
//    val hasProjects: Boolean,
//    val hasWiki: Boolean,
    // val canFork: Boolean,
    val htmlUrl: String,
    // val url: String,
    // val forksUrl: String,
    // val teamsUrl: String,
    // val eventsUrl: String,
    // val issueEventsUrl: String,
    // val issuesUrl: String,
    // val cloneUrl: String,
//    @SerializedName("allow_forking")
//    val isAllowForking: Boolean,
    // val defaultBranch: String,
    // val createdAt: String,
    // val updatedAt: String,
): SuperModelInterface

data class GithubOwnerModel(
    val login: String,
    val id: Int,
//    val avatarUrl: String,
//    val url: String,
//    val htmlUrl: String,
//    val followersUrl: String,
//    val followingUrl: String,
//    val gistsUrl: String,
//    val starredUrl: String,
//    val subscriptionsUrl: String,
//    val organizationsUrl: String,
//    val reposUrl: String,
//    val eventsUrl: String,
//    val receivedEventsUrl: String,
//    val type: String,
//    val isSiteAdmin: Boolean,
)
