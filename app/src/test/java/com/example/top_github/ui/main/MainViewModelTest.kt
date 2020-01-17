package com.example.top_github.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.top_github.R
import com.example.top_github.data.remote.repository.FetchRepository
import com.example.top_github.data.remote.response.ResponseData
import com.example.top_github.utils.Status
import com.example.top_github.utils.network.NetworkHelper
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var fetchRepository: FetchRepository

    @Mock
    private lateinit var messageStringIdObserver: Observer<Int>
    @Mock
    private lateinit var statusObserver: Observer<Status>


    private lateinit var mainViewModel: MainViewModel
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        val compositeDisposable: CompositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()

        mainViewModel = MainViewModel(compositeDisposable, networkHelper, fetchRepository,testScheduler)
        mainViewModel.messageStringId.observeForever(messageStringIdObserver)
        mainViewModel.status.observeForever(statusObserver)
    }

    @Test
    fun givenNoInternet_whenFetchingData_shouldShowNetworkError() {
        // mainViewModel.data.value=ArrayList()
        Mockito.doReturn(false).`when`(networkHelper).isInternetConnected()
        mainViewModel.callApi("Kotlin")
        assert(mainViewModel.messageStringId.value == R.string.network_connection_error)
        verify(messageStringIdObserver).onChanged(R.string.network_connection_error)
    }

    @Test
    fun giveResponse_whenFetchingData_shouldShowSuccess() {
        var response: List<ResponseData> = ArrayList()
        Mockito.doReturn(true).`when`(networkHelper).isInternetConnected()
        Mockito.doReturn(Single.just(response)).`when`(fetchRepository).fetchData("Kotlin")
        mainViewModel.callApi("Kotlin")
        testScheduler.triggerActions()
        assert(mainViewModel.status.value == Status.SUCCESS)
        verify(statusObserver).onChanged(Status.SUCCESS)


    }
}