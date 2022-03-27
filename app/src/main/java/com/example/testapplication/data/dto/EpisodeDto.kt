package com.example.testapplication.data.dto

import com.example.testapplication.domain.model.Episode

data class EpisodeDto(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
) {
    fun toEpisode(): Episode {
        return Episode(
            air_date = air_date,
            characters = characters,
            created = created,
            episode = episode,
            id = id,
            name = name,
            url = url,
        )
    }
}