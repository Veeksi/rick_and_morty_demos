package com.example.testapplication.data.remote

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testapplication.data.MortyService
import com.example.testapplication.domain.model.Character
import com.example.testapplication.domain.model.FilterCharacters

class CharactersPagingSource(
    private val service: MortyService,
    private val filter: FilterCharacters
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1

        return try {
            val pagedResponse =
                service.getCharacters(
                    page = pageNumber,
                    status = filter.status.value,
                    name = filter.name,
                    gender = filter.gender.value,
                ).toPagedResponseCharacter()
            val data = pagedResponse.results

            var nextPageNumber: Int? = null
            if (pagedResponse.pageInfo.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextPageNumber
            )

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int = 1
}