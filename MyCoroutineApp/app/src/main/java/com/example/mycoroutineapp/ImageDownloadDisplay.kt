package com.example.mycoroutineapp

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.Global
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mycoroutineapp.databinding.ActivityImageDownloadDisplayBinding
import com.example.mycoroutineapp.util.BackEndJob.Companion.saveToInternalStorage
import com.example.mycoroutineapp.util.BackEndJob.Companion.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

class ImageDownloadDisplay : AppCompatActivity() {
    private lateinit var binding: ActivityImageDownloadDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDownloadDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageURLET.setText("https://images.pexels.com/photos/730344/pexels-photo-730344.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")

        binding.downloadBtn.setOnClickListener {
            it.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE

            val result = GlobalScope.async(Dispatchers.IO) {
                val bitMapURL = URL(binding.imageURLET.text.toString())
                bitMapURL.toBitmap()
            }

            GlobalScope.launch(Dispatchers.Main) {
                val bitmap = result.await()
                bitmap?.apply {
                    val savedUri = saveToInternalStorage(this@ImageDownloadDisplay)
                    binding.displayImageIV.setImageURI(savedUri)
                }

                it.isEnabled = true
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }
}
