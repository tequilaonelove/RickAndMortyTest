package com.example.rickandmortytest.ui.details

import androidx.lifecycle.ViewModel
import com.example.rickandmortytest.data.model.Character
import com.example.rickandmortytest.data.network.CharacterDetailsSource
import com.example.rickandmortytest.extensions.launchAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterDetailsSource: CharacterDetailsSource,
) : ViewModel() {

    private val _flow = MutableStateFlow<Character?>(null)
    val singleCharacterFlow
        get() = _flow

    fun getSingleCharacter(
        id: Int,
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) = launchAsync({
        val data = characterDetailsSource.getSingleCharacter(id)
        _flow.value = data
    }, onSuccess = {
        onSuccess.invoke()
    }, onError = {
        onError(it.message.orEmpty())
    })

}