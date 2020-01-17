package com.example.top_github.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.example.top_github.R
import com.example.top_github.data.remote.response.ResponseData
import com.example.top_github.di.component.ActivityComponent
import com.example.top_github.ui.base.BaseActivity
import com.example.top_github.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailViewModel>() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onBackPressed() {
        ActivityCompat.finishAfterTransition(this);

    }

    override fun injectDependancies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun provideLayoutID(): Int = R.layout.activity_detail

    @SuppressLint("NewApi")
    override fun setupView(bundle: Bundle?) {
        supportActionBar?.title=getString(R.string.detail)
        var data: ResponseData = intent.getSerializableExtra("image") as ResponseData
        icon_details.setTransitionName(intent.getStringExtra("transition"));
        text.text = data.name
        user_name.text = data.username
        url.text = data.url
        repo_description.text = "Description : " + data.repo.description
        repo_name.text = "Name : " + data.repo.name
        repo_url.text = "Url : " + data.repo.url
        imageLoader.DisplayImage(data.avatar, R.drawable.ic_launcher_background, icon_details);
    }

}
