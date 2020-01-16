package com.example.top_github.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.top_github.data.remote.repository.FetchRepository
import com.example.top_github.data.remote.response.ResponseData
import com.example.top_github.ui.base.BaseViewModel
import com.example.top_github.utils.Status
import com.example.top_github.utils.network.NetworkHelper
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val fetchRepository: FetchRepository,private val scheduler: Scheduler
) :
    BaseViewModel(compositeDisposable, networkHelper) {
     var data: MutableLiveData<List<ResponseData>> = MutableLiveData()
     val status: MutableLiveData<Status> = MutableLiveData()

    public fun getData(): LiveData<List<ResponseData>> = data
    public fun getStatus(): LiveData<Status> = status

    public fun setdata(data:MutableLiveData<List<ResponseData>>) {
        this.data = data
    }
    fun callApi(language:String)
    {
        if (checkInternetConnectivity()) {
            status.postValue(Status.LOADING)
            compositeDisposable.add(
                fetchRepository.fetchData(language)
                    .subscribeOn(scheduler)
                    .subscribeWith(object : DisposableSingleObserver<List<ResponseData>>() {
                        override fun onSuccess(t: List<ResponseData>) {
                            data.postValue(t)
                            status.postValue(Status.SUCCESS)
                        }
                        override fun onError(e: Throwable) {
                            messageString.postValue(e.message)
                            status.postValue(Status.ERROR)

                        }
                    })
            )
        }
    }
    override fun onCreate() {

    }
}