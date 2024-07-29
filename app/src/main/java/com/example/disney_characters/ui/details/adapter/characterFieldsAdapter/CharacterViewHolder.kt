package com.example.disney_characters.ui.details.adapter.characterFieldsAdapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disney_characters.databinding.CharacterFieldBinding
import com.example.disney_characters.ui.details.adapter.fieldsItemAdapter.FieldItemsAdapter
import com.example.disney_characters.ui.models.CharacterFieldsModel

class CharacterViewHolder(private val binding: CharacterFieldBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharacterFieldsModel) {
        binding.run {
                fieldName.text = item.name
                rwFieldsItems.layoutManager = GridLayoutManager(binding.root.context, 3)
                val adapter = FieldItemsAdapter()
                rwFieldsItems.adapter = adapter
                adapter.submitList(item.movies)
        }
    }
}