package com.example.top_github.di.module

import android.R
import android.app.Activity
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.top_github.data.remote.repository.FetchRepository
import com.example.top_github.ui.detail.DetailViewModel
import com.example.top_github.ui.base.BaseActivity
import com.example.top_github.ui.main.MainAdapter
import com.example.top_github.ui.main.MainViewModel
import com.example.top_github.utils.ImageLoader
import com.example.top_github.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

@Module
class ActivityModule(private val activity: BaseActivity<*>) {
    @Provides
    fun provideActivityContext(): Activity {
        return activity
    }

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideMainViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper, fetchRepository: FetchRepository
    ): MainViewModel =
        ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(compositeDisposable, networkHelper, fetchRepository,
                    Schedulers.io()) as T
            }
        }).get(MainViewModel::class.java)

    @Provides
    fun provideDetailViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper, fetchRepository: FetchRepository
    ): DetailViewModel =
        ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetailViewModel(
                    compositeDisposable,
                    networkHelper,
                    fetchRepository
                ) as T
            }
        }).get(DetailViewModel::class.java)

    @Provides
    fun provideMainAdapter(imageLoader: ImageLoader): MainAdapter =
        MainAdapter(ArrayList(), activity, imageLoader)

    @Provides
    fun provideMainSpinnerAdaptor() : ArrayAdapter<String> = ArrayAdapter(activity,
        R.layout.simple_spinner_dropdown_item, activity.resources.getStringArray(com.example.top_github.R.array.Languages))


}