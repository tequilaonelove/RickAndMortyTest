package com.example.rickandmortytest.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmortytest.R
import com.example.rickandmortytest.databinding.FragmentDetailsBinding
import com.example.rickandmortytest.extensions.launchOnLifecycleScope
import com.example.rickandmortytest.extensions.showErrorSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args by navArgs<CharacterDetailsFragmentArgs>()
    private val detailsViewModel: CharacterDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            onLoadingState(true)

            launchOnLifecycleScope {
                detailsViewModel.singleCharacterFlow
                    .collect {
                        if (it.result != null) {
                            onLoadingState(false)
                            name.text = it.result.name
                            location.text = it.result.location.name
                            species.text = it.result.species
                            status.text = it.result.status

                            Glide.with(this@CharacterDetailsFragment)
                                .load(it.result.image)
                                .transition(DrawableTransitionOptions.withCrossFade().crossFade(500))
                                .into(image)

                        } else if (it.error != null) {
                            showErrorSnackbar(message = it.error) {
                                onLoadingState(true)
                                detailsViewModel.fetchData(args.id)
                            }
                            progress.isVisible = false
                        }
                    }
            }
        }

        detailsViewModel.fetchData(args.id)

    }

    private fun onLoadingState(isActive: Boolean) {
        binding.progress.isVisible = isActive
        binding.layout.isVisible = !isActive
    }

}