package com.example.top_github.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import com.example.top_github.R
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton
import kotlin.collections.HashMap

@Singleton
class ImageLoader(context: Context?) {
    private val inSampleSize = 0
    private var image: Bitmap? = null
    //  var memoryCache: MemoryCache = MemoryCache()
    var executorService: ExecutorService
    private val imageViewMap = Collections.synchronizedMap(HashMap<ImageView, String>())


    private lateinit var memoryCache: LruCache<String, Bitmap>


    var stub_id: Int = R.drawable.ic_launcher_background
    fun DisplayImage(url: String, loader: Int, imageView: ImageView) {
        stub_id = loader
//        imageView.setImageResource(0)
        imageViewMap[imageView] = url
        val bitmap = memoryCache.get(url)
        if (bitmap != null) {
            imageView.setImageResource(loader);
            imageView.setImageBitmap(bitmap)
        }else {
            queuePhoto(url, imageView)
            imageView.setImageResource(loader);
        }
    }

    private fun queuePhoto(url: String, imageView: ImageView) {
        val p = PhotoToLoad(url, imageView)
        executorService.submit(PhotosLoader(p))
    }

    //Task for the queue
    inner class PhotoToLoad(var url: String, var imageView: ImageView)

    internal inner class PhotosLoader(var photoToLoad:PhotoToLoad) : Runnable {
        override fun run() {
            if(isImageViewReused(photoToLoad)) return
            val bmp = getImage(photoToLoad.url)
            memoryCache!!.put(photoToLoad.url, bmp)
            if(isImageViewReused(photoToLoad)) return
            val bd = BitmapDisplayer(bmp, photoToLoad)
            val a = photoToLoad.imageView.context as Activity
            a.runOnUiThread(bd)
        }

    }

    //Used to display bitmap in the UI thread
    internal inner class BitmapDisplayer(var bitmap: Bitmap?, var photoToLoad: PhotoToLoad) : Runnable {
        override fun run() {
            if(!isImageViewReused(photoToLoad)) photoToLoad.imageView.setImageBitmap(bitmap) else photoToLoad.imageView.setImageResource(stub_id)
        }

    }
    private fun isImageViewReused(photoToLoad: PhotoToLoad): Boolean {
        val tag = imageViewMap[photoToLoad.imageView]
        return tag == null || tag != photoToLoad.url
    }


    private fun getImage(imageUrl: String): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        options.inSampleSize = inSampleSize
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            val stream = connection.inputStream
            options.inJustDecodeBounds = false
            image = BitmapFactory.decodeStream(stream, null, options)
            return image
        } catch (e: Exception) {
            Log.e("getImage", e.toString())
        }
        return image
    }

    init {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        println("cache size = $cacheSize")
        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                // The cache size will be measured in kilobytes rather than number of items.
                return bitmap.byteCount / 1024
            }
        }
        executorService = Executors.newFixedThreadPool(4)
    }
}
