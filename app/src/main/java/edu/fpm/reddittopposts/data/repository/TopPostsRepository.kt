package edu.fpm.reddittopposts.data.repository

import edu.fpm.reddittopposts.data.api.TopPostsService
import edu.fpm.reddittopposts.data.entities.RedditResponse
import edu.fpm.reddittopposts.utils.Resource
import javax.inject.Inject

class TopPostsRepository @Inject constructor(private val topPostsService: TopPostsService) {

    suspend fun getTopPostsResource(after: String, limit: String): Resource<RedditResponse> {
        val response = topPostsService.getTopPosts(after, limit)
        if (response.isSuccessful) {
            response.body()?.let { return Resource.Success(it) }
        }
        return Resource.Error(response.message())
    }

}