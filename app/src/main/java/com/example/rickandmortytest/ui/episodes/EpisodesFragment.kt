package com.example.rickandmortytest.ui.episodes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortytest.R
import com.example.rickandmortytest.data.model.Episode
import com.example.rickandmortytest.databinding.FragmentEpisodesBinding
import com.example.rickandmortytest.extensions.launchOnLifecycleScope
import com.example.rickandmortytest.extensions.showErrorSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class EpisodesFragment : Fragment(R.layout.fragment_episodes),
    EpisodesAdapter.EpisodeClickListener {

    private val binding by viewBinding(FragmentEpisodesBinding::bind)
    private val args by navArgs<EpisodesFragmentArgs>()
    private val episodesViewModel: EpisodesViewModel by viewModels()
    private val episodesAdapter by lazy { EpisodesAdapter(requireContext()) }
    private var snackbar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            rvEpisodes.adapter = episodesAdapter
            episodesAdapter.episodeClickListener = this@EpisodesFragment

            setLoadingState(true)
            episodesViewModel.getEpisodes(
                ids = args.ids,
                onError = {
                    setLoadingState(false)
                    snackbar = showErrorSnackbar(layout, it, onExecute = {
                        refreshFragment()
                    })
                    snackbar?.show()
                },
                onSuccess = {
                    setLoadingState(false)
                }
            )
        }

        launchOnLifecycleScope {
            episodesViewModel.episodes.collect {
                episodesAdapter.submitList(it)
            }
        }

        setSortingEpisodeListeners()

    }

    private fun setSortingEpisodeListeners() {
        with(episodesAdapter) {

            binding.btnByName.setOnClickListener {
                sortEpisodesByName()
                binding.rvEpisodes.scrollToPosition(0)
            }

            binding.btnByDate.setOnClickListener {
                sortEpisodesByDate()
                binding.rvEpisodes.scrollToPosition(0)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        snackbar?.dismiss()
        snackbar = null
    }

    private fun refreshFragment() {
        findNavController().navigate(
            R.id.action_EpisodesFragment_self,
            args.toBundle()
        )
    }

    private fun setLoadingState(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
        binding.rvEpisodes.isVisible = !isLoading
    }

    override fun onEpisodeClicked(episode: Episode) {
        Toast.makeText(requireContext(), "clicked id: ${episode.id}", Toast.LENGTH_SHORT).show()
    }

}