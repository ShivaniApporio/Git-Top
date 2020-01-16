package com.example.top_github.data.remote.repository

import com.example.top_github.data.remote.NetworkService
import com.example.top_github.data.remote.response.ResponseData
import io.reactivex.Single
import javax.inject.Inject

class FetchRepository @Inject constructor(public val networkService: NetworkService) {
    fun fetchData(language:String):Single<List<ResponseData>> =networkService.doRepositoryCall(language)
}