package com.example.rickandmortytest.data.network

import com.example.rickandmortytest.data.internal.toEpisode
import com.example.rickandmortytest.data.model.Episode
import retrofit2.HttpException
import javax.inject.Inject

class EpisodesSource @Inject constructor(
    private val episodeService: EpisodeService
) {

    suspend fun getEpisodes(id: String): List<Episode> {
        return try {
            val response = episodeService.getEpisodes(id)
            if (response.isSuccessful) {
                val res = response.body()
                res!!.map { it.toEpisode() }
            } else {
                throw HttpException(response)
            }
        } catch (e: HttpException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

}