package edu.fpm.reddittopposts.data.api

import edu.fpm.reddittopposts.data.entities.RedditResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopPostsService {

    @GET("/top.json")
    suspend fun getTopPosts(
        @Query("after") after: String,
        @Query("limit") limit: String
    ): Response<RedditResponse>
}