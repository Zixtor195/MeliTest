package com.example.melitest.ui.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melitest.R
import com.example.melitest.core.Constants.Companion.SITE_ID
import com.example.melitest.core.di.utilities.NetworkResult
import com.example.melitest.core.models.SearchResultItem
import com.example.melitest.core.models.SearchResultList
import com.example.melitest.databinding.FragmentSearchResultsBinding
import com.example.melitest.ui.adapters.SearchResultAdapter
import com.example.melitest.ui.viewmodels.FragmentSearchResultsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentSearchResults : Fragment(), SearchResultAdapter.SearchResultListener {


    private val viewModelFragment: FragmentSearchResultsViewModel by viewModels()
    private lateinit var binding: FragmentSearchResultsBinding
    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        setUpAppBar()
        setUpRecyclerSearchResult()
        setUpObserverGetItemsSearchFromQuery()
        super.onViewCreated(view, savedInstanceState)
    }

    /** AppBar Functions **/
    private fun setUpAppBar() {
        with(binding.widgetSearchbar) {
            inputTextSearchText.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    getItemsSearchFromQuery(SITE_ID, textView.text.toString())
                    hideKeyboard(requireContext(), inputTextSearchText)
                    true
                } else {
                    false
                }
            }
        }
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    /** GetItemsSearchFromQuery Functions **/

    private fun setUpRecyclerSearchResult() {
        with(binding.recyclerViewSearchResult) {
            searchResultAdapter = SearchResultAdapter(this@FragmentSearchResults)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = searchResultAdapter
        }
    }

    private fun getItemsSearchFromQuery(site_id: String, query: String) {
        viewModelFragment.getItemsSearchFromQuery(site_id, query)
    }

    private fun setUpObserverGetItemsSearchFromQuery() {
        viewModelFragment.getItemsSearchFromQuery.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.i("SearchResultsFragment", "GetItemsSearchFromQuery Work on success")
                    response.data?.let { handleGetTeamsResourceSuccess(it) }
                }
                is NetworkResult.Loading -> {
                    Log.i("SearchResultsFragment", "GetItemsSearchFromQuery Work on loading")
                    handleGetTeamsResourceLoading()
                }
                is NetworkResult.Error -> {
                    Log.e("SearchResultsFragment", "GetItemsSearchFromQuery Work on error" + response.message)
                    handleGetTeamsResourceError()
                }
            }
        }
    }

    private fun handleGetTeamsResourceSuccess(searchResultList: SearchResultList) {
        binding.progressBarSearchResult.visibility = View.GONE

        if (searchResultList.results.isNotEmpty()) {
            binding.textViewInformative.visibility = View.GONE
            binding.recyclerViewSearchResult.visibility = View.VISIBLE

            searchResultAdapter.updateItems(searchResultList.results)

        } else {
            binding.textViewInformative.visibility = View.VISIBLE
            binding.recyclerViewSearchResult.visibility = View.GONE
            binding.textViewInformative.text = getText(R.string.results_no_found)
        }
    }

    private fun handleGetTeamsResourceLoading() {
        binding.progressBarSearchResult.visibility = View.VISIBLE
        binding.textViewInformative.visibility = View.GONE
        binding.recyclerViewSearchResult.visibility = View.GONE
    }

    private fun handleGetTeamsResourceError() {
        binding.progressBarSearchResult.visibility = View.GONE
        binding.textViewInformative.visibility = View.VISIBLE
        binding.recyclerViewSearchResult.visibility = View.GONE
        binding.textViewInformative.text = getText(R.string.results_error)
    }


    /** OnClickListeners **/
    override fun onClickSearchResult(searchResultItem: SearchResultItem) {
        val navController = findNavController()
        val navAction = FragmentSearchResultsDirections
            .actionNavigationFragmentSearchResultsToNavigationfragmentDetailsItem(searchResultItem)

        navController.navigate(navAction)
    }

}