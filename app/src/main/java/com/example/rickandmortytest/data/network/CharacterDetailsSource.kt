package com.example.rickandmortytest.data.network

import com.example.rickandmortytest.data.internal.toCharacter
import com.example.rickandmortytest.data.model.Character
import retrofit2.HttpException
import javax.inject.Inject

class CharacterDetailsSource @Inject constructor(
    private val characterService: CharacterService
) {

    suspend fun getSingleCharacter(id: Int): Character {
        return try {
            val response = characterService.getSingleCharacter(id)
            if (response.isSuccessful) {
                val res = response.body()
                res!!.toCharacter()
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