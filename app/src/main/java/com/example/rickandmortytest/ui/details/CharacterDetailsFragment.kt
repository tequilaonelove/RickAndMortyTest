package com.example.rickandmortytest.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmortytest.R
import com.example.rickandmortytest.databinding.FragmentDetailsBinding
import com.example.rickandmortytest.extensions.launchOnLifecycleScope
import com.example.rickandmortytest.extensions.showErrorSnackbar
import com.example.rickandmortytest.ui.episodes.EpisodesFragmentArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args by navArgs<CharacterDetailsFragmentArgs>()
    private val detailsViewModel: CharacterDetailsViewModel by viewModels()
    private var snackbar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            setLoadingState(true)
            binding.button.isVisible = false

            detailsViewModel.getSingleCharacter(
                id = args.id,
                onError = { message ->
                    setLoadingState(false)
                    snackbar = showErrorSnackbar(layout, message, onExecute = {
                        refreshFragment()
                    })
                    snackbar?.show()
                },
                onSuccess = { setLoadingState(false) }
            )

            launchOnLifecycleScope {
                detailsViewModel.singleCharacterFlow.collect { character ->
                    if (character != null) {

                        name.text = character.name
                        location.text = character.location.name
                        species.text = character.species
                        status.text = character.status

                        button.setOnClickListener {
                            findNavController().navigate(
                                R.id.action_CharacterDetailsFragment_to_EpisodesFragment,
                                EpisodesFragmentArgs(
                                    ids = character.toEpisodeIds().toString()
                                ).toBundle()
                            )
                        }

                        Glide.with(this@CharacterDetailsFragment)
                            .load(character.image)
                            .transition(DrawableTransitionOptions.withCrossFade().crossFade(500))
                            .into(binding.image)

                        setLoadingState(false)
                        binding.button.isVisible = true

                    }
                }
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
            resId = R.id.action_CharacterDetailsFragment_self,
            args = args.toBundle()
        )
    }

    private fun setLoadingState(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
        binding.layout.isVisible = !isLoading
    }

}