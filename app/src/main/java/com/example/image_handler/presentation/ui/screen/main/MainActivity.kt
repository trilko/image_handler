package com.example.image_handler.presentation.ui.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.example.image_handler.R
import com.example.image_handler.data.network.model.ImageResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "TAGGS"
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var liveData: LiveData<ImageResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        liveData = mainViewModel.liveData
        mainViewModel.updateData()
        initObservers()
    }

    private fun initObservers() {
        liveData.observe(this, {
            Log.v(TAG, "hey $it")

        })
    }
}