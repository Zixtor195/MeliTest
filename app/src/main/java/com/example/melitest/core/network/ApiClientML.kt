package com.example.melitest.core.network

import com.example.melitest.core.Constants.Companion.SITE_ID
import com.example.melitest.core.models.SearchResultList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClientML {

    @GET("{site_id}/search?")
    suspend fun getItemsSearchFromQuery(
        @Path("site_id") site_id: String,
        @Query("q") query: String
    ): Response<SearchResultList>

}