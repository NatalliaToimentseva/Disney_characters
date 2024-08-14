package com.example.disney_characters.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.disney_characters.models.CharacterItemModel

class DisneyDiffUtils : DiffUtil.ItemCallback<CharacterItemModel>() {
    override fun areItemsTheSame(
        oldItem: CharacterItemModel,
        newItem: CharacterItemModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterItemModel,
        newItem: CharacterItemModel
    ): Boolean {
        return oldItem == newItem
    }
}