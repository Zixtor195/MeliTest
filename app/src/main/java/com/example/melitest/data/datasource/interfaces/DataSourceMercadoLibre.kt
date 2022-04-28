package com.example.melitest.data.datasource.interfaces

import com.example.melitest.core.models.SearchResultList
import retrofit2.Response

interface DataSourceMercadoLibre {
    suspend fun getItemsSearchFromQuery(site_id: String, query: String): Response<SearchResultList>
}