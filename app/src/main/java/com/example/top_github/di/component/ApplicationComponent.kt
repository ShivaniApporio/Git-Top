package com.example.top_github.di.component

import com.example.SampleApplication
import com.example.top_github.data.remote.NetworkService
import com.example.top_github.di.module.ApplicationModule
import com.example.top_github.utils.ImageLoader
import com.example.top_github.utils.network.NetworkHelper
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: SampleApplication)

    fun getNetworkService():NetworkService
    fun getCompositeDisposable():CompositeDisposable
    fun getNetworkHelper():NetworkHelper
    fun getImageLoader():ImageLoader

}