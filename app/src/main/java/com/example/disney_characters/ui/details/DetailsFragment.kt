package com.example.disney_characters.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disney_characters.R
import com.example.disney_characters.databinding.FragmentDetailsBinding
import com.example.disney_characters.ui.details.adapter.characterFieldsAdapter.CharacterAdapter
import com.example.disney_characters.ui.models.CharacterFieldsModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

const val ID = "id"

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isInProgress.observe(viewLifecycleOwner) { isLoad ->
            binding?.run {
                if (isLoad) {
                    progressBar.visibility = View.VISIBLE
                } else progressBar.visibility = View.GONE
            }
        }
        viewModel.character.observe(viewLifecycleOwner) { character ->
            binding?.run {
                if (character != null) {
                    characterName.text = character.name
                    if (character.imgUrl != "") {
                        Picasso.get().load(character.imgUrl).into(characterImg)
                    } else {
                        characterImg.setImageResource(R.drawable.no_heroes_here)
                    }
                    init(character.fields)
                } else {
                    characterName.text = getString(R.string.error_loading_data)
                    characterImg.setImageResource(R.drawable.no_heroes_here)
                }
            }
        }
        arguments?.let {
            viewModel.getCharacter(it.getInt(ID))
        }
        binding?.backBtn?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun init(fields: List<CharacterFieldsModel>) {
        val adapter = CharacterAdapter()
        binding?.run {
            rwFields.layoutManager = LinearLayoutManager(requireContext())
            rwFields.adapter = adapter
            adapter.submitList(fields)
        }
    }

    companion object {
        fun getInstance(id: Int): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(ID to id)
            }
        }
    }
}