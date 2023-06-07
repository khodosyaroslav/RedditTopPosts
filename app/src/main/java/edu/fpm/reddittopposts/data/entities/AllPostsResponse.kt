package edu.fpm.reddittopposts.data.entities

data class AllPostsResponse(
    val after: String,
    val before: String,
    val children: List<ChildrenResponse>
)