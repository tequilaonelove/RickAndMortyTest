package com.example.rickandmortytest.di

import android.content.Context
import coil.ImageLoader
import com.example.rickandmortytest.data.network.CharacterService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object AppModule {

    val MIMETYPE_JSON = "application/json".toMediaType()

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

    @[Provides Singleton]
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader(context)
    }
}