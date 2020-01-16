package com.example.top_github.ui.detail

import com.example.top_github.data.remote.repository.FetchRepository
import com.example.top_github.ui.base.BaseViewModel
import com.example.top_github.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

class DetailViewModel(
    private val compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val fetchRepository: FetchRepository
) : BaseViewModel(compositeDisposable, networkHelper)
{
    override fun onCreate() {
    }
}