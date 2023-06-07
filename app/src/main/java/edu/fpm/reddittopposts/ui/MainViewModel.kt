package edu.fpm.reddittopposts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.fpm.reddittopposts.data.repository.TopPostsPagingSource
import edu.fpm.reddittopposts.data.repository.TopPostsRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val postsRepository: TopPostsRepository) :
    ViewModel() {

    val postsData = Pager(
        PagingConfig(15, 10, false, 15)
    ) {
        TopPostsPagingSource(repository = postsRepository)
    }.flow.cachedIn(viewModelScope)
}