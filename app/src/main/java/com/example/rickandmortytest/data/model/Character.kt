package com.example.rickandmortytest.data.model

import android.net.Uri

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    fun toEpisodeIds(): List<Int> {
        return episode.map {
            Uri.parse(it).lastPathSegment!!.toInt()
        }
    }
}



