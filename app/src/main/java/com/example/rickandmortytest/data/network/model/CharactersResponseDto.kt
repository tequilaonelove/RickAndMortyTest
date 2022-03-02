package com.example.rickandmortytest.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseDto(
    @SerialName("info") val info: InfoDto,
    @SerialName("results") val results: List<CharacterDto>
)