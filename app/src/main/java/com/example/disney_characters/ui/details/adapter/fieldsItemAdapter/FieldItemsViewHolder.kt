package com.example.disney_characters.ui.details.adapter.fieldsItemAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.disney_characters.databinding.MovieItemBinding

class FieldItemsViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.run {
            movieName.text = item
        }
    }
}