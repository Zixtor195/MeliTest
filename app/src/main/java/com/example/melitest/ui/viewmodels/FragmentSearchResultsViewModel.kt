package com.example.melitest.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.melitest.core.di.utilities.NetworkResult
import com.example.melitest.core.models.SearchResultList
import com.example.melitest.usecases.GetItemsSearchFromQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentSearchResultsViewModel @Inject constructor(
    application: Application,
    private val getItemsSearchFromQueryUseCase: GetItemsSearchFromQueryUseCase
) : AndroidViewModel(application) {

    private val _getItemsSearchFromQuery: MutableLiveData<NetworkResult<SearchResultList>> = MutableLiveData()
    val getItemsSearchFromQuery: LiveData<NetworkResult<SearchResultList>> = _getItemsSearchFromQuery

    fun getItemsSearchFromQuery(site_id: String, query: String) =
        viewModelScope.launch {
            _getItemsSearchFromQuery.postValue(NetworkResult.Loading())
            getItemsSearchFromQueryUseCase.invoke(site_id, query).collect { values ->
                _getItemsSearchFromQuery.value = values
            }
        }

}