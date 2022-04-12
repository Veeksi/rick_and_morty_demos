package com.example.testapplication.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.testapplication.data.dto.CharacterDetailDto
import com.example.testapplication.domain.model.Character
import com.example.testapplication.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<Character>>
    suspend fun getCharacter(id: Int): Flow<Resource<Character>>
}