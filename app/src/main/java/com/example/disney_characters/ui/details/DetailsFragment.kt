package com.example.disney_characters.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disney_characters.R
import com.example.disney_characters.databinding.FragmentDetailsBinding
import com.example.disney_characters.ui.details.adapter.characterFieldsAdapter.CharacterAdapter
import com.example.disney_characters.models.CharacterFieldsModel
import com.example.disney_characters.utils.loadImg
import com.example.disney_characters.utils.toast
import dagger.hilt.android.AndroidEntryPoint

private const val ID = "id"

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private val arguments: DetailsFragmentArgs by navArgs()
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
                    if (character.imgUrl != "" && character.imgUrl != null) {
                        characterImg.loadImg(character.imgUrl)
                    } else {
                        characterImg.setImageResource(R.drawable.no_heroes_here)
                    }
                    displayCharacterFields(character.fields)
                } else {
                    characterName.text = getString(R.string.error_loading_data)
                    characterImg.setImageResource(R.drawable.no_heroes_here)
                }
            }
        }
        viewModel.error.observe(viewLifecycleOwner) { message ->
            message?.run {
                requireContext().toast(message)
                viewModel.clearError()
            }
        }
        viewModel.getCharacter(arguments.id)

        binding?.backBtn?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun displayCharacterFields(fields: List<CharacterFieldsModel>) {
        val adapter = CharacterAdapter()
        binding?.run {
            rwFields.layoutManager = LinearLayoutManager(requireContext())
            rwFields.adapter = adapter
            adapter.submitList(fields)
        }
    }
}