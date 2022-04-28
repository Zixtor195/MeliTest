package com.example.melitest.data.repositories

import com.example.melitest.core.di.utilities.BaseApiResponse
import com.example.melitest.core.di.utilities.NetworkResult
import com.example.melitest.core.models.SearchResultList
import com.example.melitest.data.datasource.interfaces.DataSourceMercadoLibre
import com.example.melitest.data.repositories.interfaces.RepositoryMercadoLibre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryMercadoLibreImpl @Inject constructor(private val dataSourceMercadoLibre: DataSourceMercadoLibre) :
    RepositoryMercadoLibre, BaseApiResponse() {

    override suspend fun getItemsSearchFromQuery(
        site_id: String,
        query: String
    ): Flow<NetworkResult<SearchResultList>> {
        return flow {
            emit(safeApiCall { dataSourceMercadoLibre.getItemsSearchFromQuery(site_id, query) })
        }.flowOn(Dispatchers.IO)
    }
}