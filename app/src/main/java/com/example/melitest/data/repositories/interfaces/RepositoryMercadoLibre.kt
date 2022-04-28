package com.example.melitest.data.repositories.interfaces

import com.example.melitest.core.di.utilities.NetworkResult
import com.example.melitest.core.models.SearchResultList
import kotlinx.coroutines.flow.Flow

interface RepositoryMercadoLibre {
    suspend fun getItemsSearchFromQuery(site_id: String, query: String): Flow<NetworkResult<SearchResultList>>
}