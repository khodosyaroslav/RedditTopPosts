package edu.fpm.reddittopposts.data.entities

data class PostResponse(
    val title: String,
    val author: String,
    val thumbnail: String,
    val num_comments: Int,
    val created: Long,
    val url: String
)