package com.example.rickandmortytest.data.network

import com.example.rickandmortytest.data.internal.toCharacter
import com.example.rickandmortytest.data.network.model.ResultCharacter
import retrofit2.HttpException
import javax.inject.Inject

class CharacterDetailsSource @Inject constructor(
    private val characterService: CharacterService
) {

    suspend fun getSingleCharacter(id: Int): ResultCharacter {
        return try {
            val response = characterService.getSingleCharacter(id)
            if (response.isSuccessful) {
                val res = response.body()
                val character = res!!.toCharacter()
                ResultCharacter(result = character)
            } else {
                ResultCharacter(error = response.message())
            }
        } catch (e: HttpException) {
            ResultCharacter(error = e.message())
        } catch (e: Exception) {
            ResultCharacter(error = e.message?: "An error occurred")
        }
    }
}