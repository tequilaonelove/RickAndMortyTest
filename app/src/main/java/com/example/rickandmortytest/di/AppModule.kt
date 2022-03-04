package com.example.rickandmortytest.di

import com.example.rickandmortytest.data.network.CharacterService
import com.example.rickandmortytest.data.network.EpisodeService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object AppModule {

    private val MIMETYPE_JSON = "application/json".toMediaType()

    @[Provides Singleton]
    fun provideJson(): Json {
        return Json(Json.Default) {
            ignoreUnknownKeys = true
        }
    }

    @[Provides Singleton]
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return logging
    }

    @ExperimentalSerializationApi
    @[Provides Singleton]
    fun provideCharacterService(json: Json, loggingInterceptor: HttpLoggingInterceptor): CharacterService {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com")
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory(MIMETYPE_JSON))
            .build()

        return retrofit.create(CharacterService::class.java)
    }

    @ExperimentalSerializationApi
    @[Provides Singleton]
    fun provideEpisodeService(json: Json, loggingInterceptor: HttpLoggingInterceptor): EpisodeService {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com")
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory(MIMETYPE_JSON))
            .build()

        return retrofit.create(EpisodeService::class.java)
    }

}