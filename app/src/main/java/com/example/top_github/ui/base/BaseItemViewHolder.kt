package com.example.top_github.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.top_github.R

abstract class BaseItemViewHolder(@LayoutRes layoutID:Int, parent:ViewGroup):
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutID, parent, false))
{
    init {
        onCreate()
    }
    protected fun onCreate() {
        setupView(itemView)
    }
    abstract fun setupView(view: View)
}
