package com.example.melitest.usecases

import com.example.melitest.core.di.utilities.NetworkResult
import com.example.melitest.core.models.SearchResultItem
import com.example.melitest.core.models.SearchResultList
import com.example.melitest.data.repositories.interfaces.RepositoryMercadoLibre
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetItemsSearchFromQueryUseCaseTest {

    @RelaxedMockK
    private lateinit var repositoryMercadoLibre: RepositoryMercadoLibre
    lateinit var getItemsSearchFromQueryUseCase: GetItemsSearchFromQueryUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getItemsSearchFromQueryUseCase = GetItemsSearchFromQueryUseCase(repositoryMercadoLibre)
    }

    @Test
    fun `when GetItemsSearchFromQueryUseCase Is Invoked Get NetworkResult Of SearchResultList`() = runBlocking {
        //Given
        val searchResult = SearchResultList()
        searchResult.results = listOf(SearchResultItem("123", "123", 20.3, "123"))
        val result = flowOf(NetworkResult.Success(searchResult))

        coEvery { repositoryMercadoLibre.getItemsSearchFromQuery(any(), any()) } returns result

        //When

        val response = getItemsSearchFromQueryUseCase("", "")

        //Then

        coVerify(exactly = 1) { repositoryMercadoLibre.getItemsSearchFromQuery(any(), any()) }
        assert(result == response)

    }

}