package com.example.melitest.usecases

import com.example.melitest.core.di.utilities.NetworkResult
import com.example.melitest.core.models.SearchResultList
import com.example.melitest.data.repositories.interfaces.RepositoryMercadoLibre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsSearchFromQueryUseCase @Inject constructor(private val repositoryMercadoLibre: RepositoryMercadoLibre) {
    suspend operator fun invoke(site_id: String, query: String): Flow<NetworkResult<SearchResultList>> {
        return repositoryMercadoLibre.getItemsSearchFromQuery(site_id, query)
    }
}


