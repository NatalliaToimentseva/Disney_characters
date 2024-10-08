package com.example.disney_characters.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.disney_characters.R
import com.example.disney_characters.databinding.DisneyListItemBinding
import com.example.disney_characters.models.CharacterItemModel
import com.example.disney_characters.utils.loadImg

class DisneyViewHolder(private var binding: DisneyListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharacterItemModel, onClick: (id: Int) -> Unit) {
        binding.run {
            characterName.text = item.name
            if (item.imageUrl != null) {
                disneyImg.loadImg(item.imageUrl)
            } else {
                disneyImg.setImageResource(R.drawable.no_heroes_here)
            }
            disneyItem.setOnClickListener {
                onClick(item.id)
            }
        }
    }
}