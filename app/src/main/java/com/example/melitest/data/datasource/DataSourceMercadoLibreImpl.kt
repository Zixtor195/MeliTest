package com.example.melitest.data.datasource

import com.example.melitest.data.datasource.interfaces.DataSourceMercadoLibre
import com.example.melitest.core.network.ApiClientML
import javax.inject.Inject

class DataSourceMercadoLibreImpl @Inject constructor(private val apiClientML: ApiClientML) : DataSourceMercadoLibre {

    override suspend fun getItemsSearchFromQuery(site_id: String, query: String) =
        apiClientML.getItemsSearchFromQuery(site_id, query)

}