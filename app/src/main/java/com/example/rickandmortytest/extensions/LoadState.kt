package com.example.rickandmortytest.extensions

import androidx.fragment.app.Fragment
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.rickandmortytest.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showErrorSnackbar(state: CombinedLoadStates, action: (() -> Unit)? = null) {
    if (state.refresh is LoadState.Error ||
        state.append is LoadState.Error ||
        state.prepend is LoadState.Error
    ) {
        val text = state.asErrorMessage()
            ?: getString(R.string.error_message_retry_again_later)

        Snackbar.make(requireView(), text, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry_text)) { action?.invoke() }
            .show()
    }
}

internal fun CombinedLoadStates.asErrorMessage(): String? {
    return when {
        refresh is LoadState.Error -> refresh.asLoadStateError().error.message
        prepend is LoadState.Error -> prepend.asLoadStateError().error.message
        append is LoadState.Error -> append.asLoadStateError().error.message
        else -> null
    }
}

internal fun LoadState.asLoadStateError(): LoadState.Error = this as LoadState.Error