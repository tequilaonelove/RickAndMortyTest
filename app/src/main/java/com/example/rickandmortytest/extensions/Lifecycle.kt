package com.example.rickandmortytest.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

fun Fragment.launchOnLifecycleScope(execute: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        execute()
    }
}