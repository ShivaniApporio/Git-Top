package com.example.top_github.data.remote.response
import java.io.Serializable

data class ResponseData(
    val avatar: String,
    val name: String,
    val repo: Repo,
    val type: String,
    val url: String,
    val username: String
): Serializable {
    data class Repo(
        val description: String,
        val name: String,
        val url: String
    ):Serializable
}