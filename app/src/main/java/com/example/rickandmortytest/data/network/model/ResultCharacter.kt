package com.example.rickandmortytest.data.network.model

import com.example.rickandmortytest.data.model.Character

data class ResultCharacter(
    val error: String? = null,
    val result: Character? = null
)