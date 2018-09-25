package com.toapplication.wordpressapp.webservice

import com.toapplication.wordpressapp.data.categorymodel.BaseCategoryResponse
import com.toapplication.wordpressapp.data.postmodel.BasePostResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IWebServiceClient {
    @GET("get_recent_posts/")
    fun getRecentPosts(@Query("page") page: Int): Single<BasePostResponse>

//    @GET("get_tag_index/")
//    abstract fun getTags(): Single<BaseTagResponse>

    @GET("get_category_index/")
    fun getCategories(): Single<BaseCategoryResponse>

    @GET("get_category_posts/")
    fun getCategoryPosts(@Query("id") id: Int, @Query("page") page: Int): Single<BasePostResponse>

    @GET("get_search_results/")
    fun getSearchResponse(@Query("search") searchString: String): Single<BasePostResponse>
}