package com.example.top_github.ui.base

import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.SampleApplication
import com.example.top_github.di.component.ActivityComponent
import com.example.top_github.di.component.DaggerActivityComponent
import com.example.top_github.di.component.DaggerApplicationComponent
import com.example.top_github.di.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    @Inject
    lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependancies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutID())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()

    }

    private fun buildActivityComponent(): ActivityComponent =
        DaggerActivityComponent.builder().applicationComponent((application as SampleApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build()

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it?.run {
                showMessage(this)
            }
        })
        viewModel.messageStringId.observe(this, Observer {
            it?.run {
                showMessage(this)
            }
        })
    }

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        super.onBackPressed()
    }


    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    protected abstract fun injectDependancies(activityComponent: ActivityComponent)
    protected abstract fun provideLayoutID(): Int
    abstract protected fun setupView(bundle: Bundle?)


}