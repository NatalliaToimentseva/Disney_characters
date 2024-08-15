package com.example.disney_characters.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.disney_characters.R
import com.example.disney_characters.databinding.FragmentHomeBinding
import com.example.disney_characters.ui.details.DetailsFragment
import com.example.disney_characters.ui.home.adapter.DisneyAdapter
import com.example.disney_characters.models.CharacterItemModel
import com.example.disney_characters.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
    private var adapter: DisneyAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
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
        viewModel.disneyCharactersList.observe(viewLifecycleOwner) { characters ->
            if (characters.isNotEmpty()) init(characters)
        }
        viewModel.showError = { error ->
            error.message?.let { requireContext().toast(it) }
        }
        viewModel.loadListData()
    }

    private fun init(items: List<CharacterItemModel>) {
        binding?.run {
            recycleView.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = DisneyAdapter { id ->
                parentFragmentManager.beginTransaction()
                    .add(R.id.container, DetailsFragment.getInstance(id))
                    .addToBackStack(null)
                    .commit()
            }.also {
                recycleView.adapter = it
            }
            adapter?.submitList(items)
        }
    }
}