package com.example.top_github.di.component

import com.example.top_github.di.ActivityScope
import com.example.top_github.di.module.ActivityModule
import com.example.top_github.ui.detail.DetailActivity
import com.example.top_github.ui.main.MainActivity
import dagger.Component
@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)

}