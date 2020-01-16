package com.example.top_github.data.remote

import com.example.top_github.data.remote.response.ResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET(EndPoints.REPOSITORIES)
    fun doRepositoryCall(
        @Query("language") language:String
    ):Single<List<ResponseData>>

}