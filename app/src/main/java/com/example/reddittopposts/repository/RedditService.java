package com.example.reddittopposts.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditService {
    @GET("/top.json")
    Call<String> STRING_CALL(
            @Query("limit") int limit,
            @Query("after") String after
    );
}
