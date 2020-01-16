package com.example.top_github.di.module

import android.app.Application
import com.example.SampleApplication
import com.example.top_github.BuildConfig
import com.example.top_github.data.remote.NetworkService
import com.example.top_github.data.remote.Networking
import com.example.top_github.utils.ImageLoader
import com.example.top_github.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule (private  val application: SampleApplication){
    @Provides
    @Singleton
    fun provideApplicationContext():Application=application;

    @Provides
    @Singleton
    fun provideNetworkService():NetworkService = Networking.create(BuildConfig.BASE_URL,
        application.cacheDir,10*1024*1024)

    @Singleton
    @Provides
    fun provideNetworkHelper():NetworkHelper= NetworkHelper(application)

    @Provides
    fun provideCompositeDisposable():CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun provideImageloader():ImageLoader= ImageLoader(application)
}