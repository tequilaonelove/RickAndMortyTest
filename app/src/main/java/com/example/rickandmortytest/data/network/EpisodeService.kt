package com.example.rickandmortytest.data.network

import com.example.rickandmortytest.data.network.model.EpisodeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeService {

    @GET("/api/episode/{ids}")
    suspend fun getEpisodes(@Path("ids") ids: String): Response<List<EpisodeDto>>
}