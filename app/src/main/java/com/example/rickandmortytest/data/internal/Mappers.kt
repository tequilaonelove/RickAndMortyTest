package com.example.rickandmortytest.data.internal

import com.example.rickandmortytest.data.model.Character
import com.example.rickandmortytest.data.model.Episode
import com.example.rickandmortytest.data.model.Location
import com.example.rickandmortytest.data.model.Origin
import com.example.rickandmortytest.data.network.model.CharacterDto
import com.example.rickandmortytest.data.network.model.EpisodeDto
import com.example.rickandmortytest.data.network.model.LocationDto
import com.example.rickandmortytest.data.network.model.OriginDto

internal fun CharacterDto.toCharacter(): Character {
    return Character(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location.toLocation(),
        name = name,
        origin = origin.toOrigin(),
        species = species,
        status = status,
        type = type,
        url = url
    )
}

internal fun EpisodeDto.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = air_date,
        episode = episode,
        characters = characters,
        url = url,
        created = created
    )
}

internal fun LocationDto.toLocation(): Location {
    return Location(name = name, url = url)
}

internal fun OriginDto.toOrigin(): Origin {
    return Origin(name = name, url = url)
}
