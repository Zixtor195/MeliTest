package com.example.melitest.ui.viewmodels

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.melitest.core.di.utilities.NetworkResult
import com.example.melitest.core.models.SearchResultItem
import com.example.melitest.core.models.SearchResultList
import com.example.melitest.usecases.GetItemsSearchFromQueryUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FragmentSearchResultsViewModelTest {

    @RelaxedMockK
    private lateinit var application: Application

    @RelaxedMockK
    private lateinit var getItemsSearchFromQueryUseCase: GetItemsSearchFromQueryUseCase

    private lateinit var fragmentSearchResultsViewModel: FragmentSearchResultsViewModel


    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        fragmentSearchResultsViewModel = FragmentSearchResultsViewModel(application, getItemsSearchFromQueryUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getItemsSearchFromQuery Is getItemsSearchFromQuery liveData is Update`() = runTest {
        //Given
        val searchResult = SearchResultList()
        searchResult.results = listOf(SearchResultItem("123", "123", 20.3, "123"))
        val result = flowOf(NetworkResult.Success(searchResult))

        coEvery { getItemsSearchFromQueryUseCase(any(), any()) } returns result

        //When

        fragmentSearchResultsViewModel.getItemsSearchFromQuery("", "")

        //Then

        coVerify(exactly = 1) { getItemsSearchFromQueryUseCase.invoke(any(), any()) }
        assert(fragmentSearchResultsViewModel.getItemsSearchFromQuery.value == result.first())
    }

}