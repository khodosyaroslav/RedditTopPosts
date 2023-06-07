package edu.fpm.reddittopposts.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import edu.fpm.reddittopposts.data.entities.ChildrenResponse
import java.lang.Exception
import javax.inject.Inject

class TopPostsPagingSource @Inject constructor(private val repository: TopPostsRepository) :
    PagingSource<String, ChildrenResponse>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, ChildrenResponse> {
        return try {
            val startPost = params.key.toString()
            val postsLimit = params.loadSize.toString()

            val allPostsResponse = repository.getTopPostsResource(startPost, postsLimit)
            val responseData = allPostsResponse.data?.data?.children ?: emptyList()

            LoadResult.Page(
                data = responseData,
                prevKey = allPostsResponse.data?.data?.before,
                nextKey = allPostsResponse.data?.data?.after
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, ChildrenResponse>): String? {
        return null
    }
}