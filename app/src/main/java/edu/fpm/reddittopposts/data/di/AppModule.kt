package edu.fpm.reddittopposts.data.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.fpm.reddittopposts.R
import edu.fpm.reddittopposts.data.api.TopPostsService
import edu.fpm.reddittopposts.data.repository.TopPostsRepository
import edu.fpm.reddittopposts.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): TopPostsService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TopPostsService::class.java)

    @Provides
    @Singleton
    fun provideRepository(topPostsService: TopPostsService): TopPostsRepository =
        TopPostsRepository(topPostsService)
}