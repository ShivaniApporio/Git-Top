package com.example

import android.app.Application
import android.util.Log
import com.example.top_github.data.remote.NetworkService
import com.example.top_github.di.component.ApplicationComponent
import com.example.top_github.di.component.DaggerApplicationComponent
import com.example.top_github.di.module.ApplicationModule
import javax.inject.Inject

class SampleApplication : Application() {
    @Inject
    lateinit var networkService: NetworkService

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent =
            DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)

    }
}