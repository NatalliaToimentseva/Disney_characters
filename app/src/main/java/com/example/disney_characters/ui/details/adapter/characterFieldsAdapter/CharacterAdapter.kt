package com.example.disney_characters.ui.details.adapter.characterFieldsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.disney_characters.databinding.CharacterFieldBinding
import com.example.disney_characters.ui.models.CharacterFieldsModel

class CharacterAdapter : ListAdapter<CharacterFieldsModel, CharacterViewHolder>(object :
    DiffUtil.ItemCallback<CharacterFieldsModel>() {

    override fun areItemsTheSame(oldItem: CharacterFieldsModel, newItem: CharacterFieldsModel): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: CharacterFieldsModel, newItem: CharacterFieldsModel): Boolean {
        return false
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}