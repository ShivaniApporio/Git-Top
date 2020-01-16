package com.example.top_github.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import com.example.top_github.R
import com.example.top_github.data.remote.response.ResponseData
import com.example.top_github.ui.detail.DetailActivity
import com.example.top_github.ui.base.BaseItemViewHolder
import com.example.top_github.utils.ImageLoader
import kotlinx.android.synthetic.main.main_item_view_holder.view.*

class MainItemViewHolder(
    parent: ViewGroup,
    private val context: Context,
    private val imageLoader: ImageLoader
) : BaseItemViewHolder(R.layout.main_item_view_holder, parent) {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)

    override fun setupView(view: View) {
    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun bind(data: ResponseData) {
        itemView.text.text = data.name
        itemView.user_name.text = data.username
        imageLoader.DisplayImage(data.avatar, R.drawable.ic_launcher_background, itemView.icon);
        itemView.setOnClickListener {
            val detailIntent = Intent(context, DetailActivity::class.java)
            val imageViewPair =
                androidx.core.util.Pair.create<View, String>(itemView.icon, "transition"+ adapterPosition)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity,
                imageViewPair
            )

            detailIntent.putExtra("image",data) // pass your bundle data
            detailIntent.putExtra("transition","transition" + adapterPosition) // pass your bundle data

            context.startActivity(detailIntent, options.toBundle())
        }
        itemView.icon.setTransitionName("transition" + adapterPosition);

    }

}