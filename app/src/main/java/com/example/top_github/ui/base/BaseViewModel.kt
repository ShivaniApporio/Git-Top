package com.example.top_github.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.top_github.R
import com.example.top_github.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    private val compositeDisposable: CompositeDisposable,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    val messageStringId: MutableLiveData<Int> = MutableLiveData()
    val messageString: MutableLiveData<String> = MutableLiveData()

    protected fun checkInternetConnectivity(): Boolean = if (networkHelper.isInternetConnected()) {
        true
    } else {
        messageStringId.postValue(R.string.network_connection_error)
        false
    }
    abstract fun onCreate()

}