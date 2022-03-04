package com.example.rickandmortytest.extensions

import androidx.fragment.app.Fragment
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.rickandmortytest.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showErrorSnackbar(loadStates: CombinedLoadStates, onExecute: (() -> Unit)? = null) {
    if (loadStates.refresh is LoadState.Error ||
        loadStates.append is LoadState.Error ||
        loadStates.prepend is LoadState.Error
    ) {
        val text = loadStates.asErrorMessage()
            ?: getString(R.string.error_message_retry_again_later)

        Snackbar.make(requireView(), text, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry_text)) { onExecute?.invoke() }
            .show()
    }
}

fun Fragment.showErrorSnackbar(message: String?, onExecute: (() -> Unit)? = null) {
    val text = message ?: getString(R.string.error_message_retry_again_later)
    Snackbar.make(requireView(), text, Snackbar.LENGTH_INDEFINITE)
        .setAction(getString(R.string.retry_text)) { onExecute?.invoke() }
        .show()
}

internal fun CombinedLoadStates.asErrorMessage(): String? {
    return (refresh as LoadState.Error).error.message
        ?: (prepend as LoadState.Error).error.message
        ?: (append as LoadState.Error).error.message
}