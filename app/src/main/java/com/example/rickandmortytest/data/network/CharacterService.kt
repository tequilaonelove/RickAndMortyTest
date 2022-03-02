package com.example.rickandmortytest.data.network

import androidx.annotation.IntRange
import com.example.rickandmortytest.data.network.model.CharactersResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/api/character")
    suspend fun character(
        @Query("page") @IntRange(from = 1) page: Int = 1
    ): Response<CharactersResponseDto>

    companion object {
        const val MAX_PAGE_SIZE = 20
    }
}