package com.example.disney_characters.ui.details.adapter.fieldsItemAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.disney_characters.databinding.MovieItemBinding

class FieldItemsAdapter :
    ListAdapter<String, FieldItemsViewHolder>(object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return false
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldItemsViewHolder {
        return FieldItemsViewHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FieldItemsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}