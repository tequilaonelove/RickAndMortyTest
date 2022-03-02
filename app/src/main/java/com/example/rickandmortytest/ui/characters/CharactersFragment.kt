package com.example.rickandmortytest.ui.characters

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortytest.R
import com.example.rickandmortytest.databinding.FragmentCharactersBinding
import com.example.rickandmortytest.extensions.showErrorSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val viewBinding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        CharacterAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            rvCharacters.adapter = adapter.withLoadStateFooter(CharacterLoaderStateAdapter())

            adapter.addLoadStateListener { state ->
                rvCharacters.isVisible = state.refresh != LoadState.Loading
                progress.isVisible = state.refresh == LoadState.Loading

                showErrorSnackbar(state = state, action = {
                    adapter.retry()
                })
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.characters.collectLatest(adapter::submitData)
            }
        }

        viewModel.fetchCharacters()

    }


}