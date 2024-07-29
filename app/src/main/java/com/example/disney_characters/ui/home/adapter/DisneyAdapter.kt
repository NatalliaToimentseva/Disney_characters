package com.example.disney_characters.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.disney_characters.databinding.DisneyListItemBinding
import com.example.disney_characters.ui.models.CharacterItemModel

class DisneyAdapter(private val onClick: (id: Int) -> Unit) :
    ListAdapter<CharacterItemModel, DisneyViewHolder>(DisneyDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyViewHolder {
        return DisneyViewHolder(
            DisneyListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DisneyViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}