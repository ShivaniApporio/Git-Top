package com.example.top_github.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.top_github.R
import com.example.top_github.di.component.ActivityComponent
import com.example.top_github.ui.base.BaseActivity
import com.example.top_github.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {
    companion object {
        const val TAG = "MainActivity"
    }

    @Inject
    lateinit var mainAdapter: MainAdapter
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var adapter: ArrayAdapter<String>


    override fun injectDependancies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun provideLayoutID(): Int = R.layout.activity_main

    override fun setupView(bundle: Bundle?) {
        supportActionBar?.title=getString(R.string.repository)
        spinner.adapter = adapter
        rv_list.layoutManager = linearLayoutManager
        rv_list.adapter = mainAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) = viewModel.callApi(parent?.getItemAtPosition(position).toString())
        }
    }
    override fun setupObservers() {
        super.setupObservers()
        viewModel.getData().observe(this, Observer {
         mainAdapter.appenddata(it)

        })
        viewModel.status.observe(this, Observer {
            if (it.equals(Status.LOADING)) {
                pb_loading.visibility = View.VISIBLE
                rv_list.visibility = View.GONE
                rv_list.smoothScrollToPosition(0);

            } else {
                pb_loading.visibility = View.GONE
                rv_list.visibility = View.VISIBLE
            }
        })
    }


}
