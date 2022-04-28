package com.example.melitest.core.models

import com.google.gson.annotations.SerializedName

class SearchResultList {
    @SerializedName("results") var results : List<SearchResultItem> = emptyList()
}