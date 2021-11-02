package com.example.image_handler.presentation.ui.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.image_handler.R
import com.example.image_handler.presentation.model.ImageUI

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var list: List<ImageUI> = ArrayList()

    fun updateData(list: List<ImageUI>) {
        this.list = list
        notifyDataSetChanged() // Bad approach, we should use diffUtil instead this. It's just for MVP speed
    }

    fun clearData() {
        this.list = emptyList()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.image_view_logo)
        private val title: TextView = itemView.findViewById(R.id.text_title)
        private val dateTaken: TextView = itemView.findViewById(R.id.text_date_taken)
        private val datePublished: TextView = itemView.findViewById(R.id.text_date_published)

        fun bind(model: ImageUI) {
            Glide.with(imageView)
                .load(model.content)
                .into(imageView)
            title.text = model.title
            dateTaken.text = model.dateTaken
            datePublished.text = model.datePublished
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}