package com.example.rickandmortytest.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortytest.data.model.Character
import com.example.rickandmortytest.data.network.CharacterPagingSource
import com.example.rickandmortytest.data.network.CharacterService
import com.example.rickandmortytest.extensions.launchPageAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject internal constructor(
    private val characterPagingSource: CharacterPagingSource
) : ViewModel() {

    private lateinit var _charactersFlow: Flow<PagingData<Character>>
    val characters
        get() = _charactersFlow

    init {
        getCharacters()
    }

    private fun getPager(): Pager<Int, Character> {
        return Pager(
            PagingConfig(
                pageSize = CharacterService.MAX_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { characterPagingSource }
        )
    }

    fun getCharacters() = launchPageAsync({
        getPager().flow
    }, onSuccess = { flow ->
        _charactersFlow = flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    })
}
