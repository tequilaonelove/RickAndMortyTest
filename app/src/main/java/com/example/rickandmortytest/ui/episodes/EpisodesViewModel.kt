package com.example.rickandmortytest.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortytest.data.model.Episode
import com.example.rickandmortytest.data.network.EpisodesSource
import com.example.rickandmortytest.extensions.launchAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodesSource: EpisodesSource
) : ViewModel() {

    private val _episodesFlow = MutableStateFlow<List<Episode>>(listOf())

    val episodes
        get() = _episodesFlow
            .asStateFlow()
            .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    fun getEpisodes(
        ids: String,
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) = launchAsync({
        val data = episodesSource.getEpisodes(ids)
        _episodesFlow.value = data
    }, onSuccess = {
        onSuccess.invoke()
    }, onError = {
        onError(it.message.orEmpty())
    })

}