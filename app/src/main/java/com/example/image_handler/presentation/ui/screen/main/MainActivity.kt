package com.example.image_handler.presentation.ui.screen.main

import android.media.Image
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.image_handler.R
import com.example.image_handler.domain.model.SortedOrder
import com.example.image_handler.domain.model.SortedOrder.*
import com.example.image_handler.presentation.model.ErrorUI
import com.example.image_handler.presentation.model.ImageUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var liveData: LiveData<List<ImageUI>>
    private lateinit var liveError: LiveData<ErrorUI>

    private lateinit var noSortedButton: Button
    private lateinit var sortedByTakenDate: Button
    private lateinit var sortedByPublishedDate: Button

    private lateinit var recyclerView: RecyclerView
    private val adapter: ImageAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noSortedButton = findViewById(R.id.get_no_sorted)
        sortedByTakenDate = findViewById(R.id.get_sorted_by_taken_date)
        sortedByPublishedDate = findViewById(R.id.get_sorted_by_publish_date)
        recyclerView = findViewById(R.id.recycler_view)

        liveData = mainViewModel.liveData
        liveError = mainViewModel.liveError

        mainViewModel.getImages(NO_SORTED)
        initAdapter()
        initObservers()
        initClickListeners()
    }

    private fun initAdapter() {
        recyclerView.adapter = adapter
    }

    private fun initObservers() {
        liveData.observe(this, {
            adapter.updateData(it)
        })
    }

    private fun initClickListeners() {
        noSortedButton.setOnClickListener {
            mainViewModel.getImages(NO_SORTED)
        }
        sortedByTakenDate.setOnClickListener {
            mainViewModel.getImages(TAKEN_DATE)
        }
        sortedByPublishedDate.setOnClickListener {
            mainViewModel.getImages(PUBLISH_DATE)
        }
    }
}