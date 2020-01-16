package com.example.top_github.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.top_github.data.remote.response.ResponseData
import com.example.top_github.utils.ImageLoader

class MainAdapter(
    private val dataList: MutableList<ResponseData>,
    private val context: Context,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<MainItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder =
        MainItemViewHolder(parent, context, imageLoader)

    override fun getItemCount(): Int = dataList.size

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun appenddata(dataList: List<ResponseData>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }



}