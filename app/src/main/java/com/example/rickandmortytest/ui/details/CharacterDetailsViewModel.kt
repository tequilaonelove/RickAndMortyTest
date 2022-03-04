package com.example.rickandmortytest.ui.details

import androidx.lifecycle.ViewModel
import com.example.rickandmortytest.data.network.CharacterDetailsSource
import com.example.rickandmortytest.data.network.model.ResultCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterDetailsSource: CharacterDetailsSource,
) : ViewModel() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val _flow = MutableStateFlow(ResultCharacter())
    val singleCharacterFlow
        get() = _flow

    fun fetchData(id: Int) = scope.launch {
        try {
            val data = characterDetailsSource.getSingleCharacter(id = id)
            _flow.value = data
        } catch (e: Exception) {
            _flow.value = ResultCharacter(e.message)
        }
    }

//    fun getClharacter(id: Int) = launchAsync({
//        newFlow(id)
//    }, { flow ->
//        viewModelScope.launch {
//            _flow.value = flow.lastOrNull()
//                ?: newFlow(id).last()
//        }
//    })
//
//    private suspend fun newFlow(id: Int) = flowOf()

}