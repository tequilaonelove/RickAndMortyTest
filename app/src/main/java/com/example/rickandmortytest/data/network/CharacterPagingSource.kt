package com.example.rickandmortytest.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortytest.data.internal.toCharacter
import com.example.rickandmortytest.data.model.Character
import retrofit2.HttpException
import javax.inject.Inject

class CharacterPagingSource @Inject internal constructor(
    private val characterService: CharacterService
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val response = characterService.getAllCharacters(page = pageNumber)
            return if (response.isSuccessful) {

                val res = response.body()
                val characters = res!!.results.map { it.toCharacter() }

                val nextPageNumber = if (characters.isEmpty() || res.info.next.isNullOrEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null

                LoadResult.Page(characters, prevPageNumber, nextPageNumber)

            } else {
                LoadResult.Error(HttpException(response))
            }

        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}