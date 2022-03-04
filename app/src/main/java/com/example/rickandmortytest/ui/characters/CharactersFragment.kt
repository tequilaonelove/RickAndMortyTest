package com.example.rickandmortytest.ui.characters

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortytest.R
import com.example.rickandmortytest.data.model.Character
import com.example.rickandmortytest.databinding.FragmentCharactersBinding
import com.example.rickandmortytest.extensions.launchOnLifecycleScope
import com.example.rickandmortytest.extensions.showErrorSnackbar
import com.example.rickandmortytest.ui.details.CharacterDetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters),
    CharacterAdapter.CharacterClickListener {

    private val viewBinding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        CharacterAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            rvCharacters.adapter = adapter.withLoadStateFooter(CharacterLoaderStateAdapter())

            adapter.characterClickListener = this@CharactersFragment
            adapter.addLoadStateListener { state ->
                rvCharacters.isVisible = state.refresh != LoadState.Loading
                progress.isVisible = state.refresh == LoadState.Loading
                showErrorSnackbar(state) { adapter.retry() }
            }
        }

        launchOnLifecycleScope {
            viewModel.characters.collectLatest(adapter::submitData)
        }

    }

    override fun onCharacterClicked(character: Character) {
        val args = CharacterDetailsFragmentArgs(character.id)
        findNavController().navigate(
            R.id.action_CharactersFragment_to_CharacterDetailsFragment,
            args.toBundle()
        )
    }

}