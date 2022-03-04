package com.example.rickandmortytest.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <T> ViewModel.launchPageAsync(
    crossinline execute: suspend () -> Flow<T>,
    crossinline onSuccess: (Flow<T>) -> Unit,
    noinline onError: ((Exception) -> Unit)? = null,
) {
    viewModelScope.launch {
        try {
            val result = execute()
            onSuccess(result)
        } catch (e: Exception) {
            onError?.invoke(e)
        }
    }
}

inline fun <T> ViewModel.launchAsync(
    crossinline execute: suspend () -> T,
    crossinline onSuccess: (T) -> Unit,
    noinline onError: ((Exception) -> Unit)? = null,
) {
    viewModelScope.launch {
        try {
            val result = execute()
            onSuccess(result)
        } catch (e: Exception) {
            onError?.invoke(e)
        }
    }
}


