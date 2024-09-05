package com.example.disney_characters.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.disney_characters.R
import com.example.disney_characters.databinding.FragmentHomeBinding
import com.example.disney_characters.ui.home.adapter.DisneyAdapter
import com.example.disney_characters.models.CharacterItemModel
import com.example.disney_characters.ui.customView.ViewBanner
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
            if (characters != null) {
                init(characters)
                binding?.banner?.run {
                    visibility = View.VISIBLE
                    getBannerState(ViewBanner.BannerState.Success(resources.getString(R.string.banner_success)))
                }
            } else {
                init(arrayListOf())
                binding?.banner?.run {
                    visibility = View.VISIBLE
                    getBannerState(ViewBanner.BannerState.Warning(resources.getString(R.string.banner_warning)))
                }
            }
        }
        viewModel.error.observe(viewLifecycleOwner) { message ->
            message?.run {
                binding?.banner?.visibility = View.VISIBLE
                binding?.banner?.getBannerState(ViewBanner.BannerState.Error(message))
                viewModel.clearError()
            }
        }
        viewModel.isFavoriteList.observe(viewLifecycleOwner) { isFavoriteList ->
            if (isFavoriteList) {
                binding?.run {
                    favoriteNotes.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.btn_background_selected
                    )
                    allNotes.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.btn_background_transparent
                    )
                }
            }
        }
        viewModel.isAllList.observe(viewLifecycleOwner) { isAllList ->
            if (isAllList) {
                binding?.run {
                    favoriteNotes.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.btn_background_transparent
                    )
                    allNotes.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.btn_background_selected
                    )
                }
            }
        }
        viewModel.loadListData()

        binding?.allNotes?.setOnClickListener {
            viewModel.selectAllList()
        }

        binding?.favoriteNotes?.setOnClickListener {
            viewModel.selectFavorite()
        }

        binding?.banner?.closeBanner {
            binding?.banner?.visibility = View.GONE
        }
    }

    private fun init(items: List<CharacterItemModel>) {
        binding?.run {
            recycleView.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = DisneyAdapter { id ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                        id
                    )
                )
            }.also {
                recycleView.adapter = it
            }
            adapter?.submitList(items)
        }
    }
}