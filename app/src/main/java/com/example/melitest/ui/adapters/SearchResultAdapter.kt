package com.example.melitest.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.melitest.R
import com.example.melitest.core.models.SearchResultItem
import com.example.melitest.databinding.ItemlistSearchResultBinding
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class SearchResultAdapter(private val listener: SearchResultListener) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    private var searchResultItems: List<SearchResultItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = searchResultItems[position]
        holder.bind(team)
    }

    override fun getItemCount() = searchResultItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(SearchResultItems: List<SearchResultItem>) {
        this.searchResultItems = SearchResultItems
        this.notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemlistSearchResultBinding, private val listener: SearchResultListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchResultItem: SearchResultItem) {
            binding.textViewItemTitle.text = searchResultItem.title

            val decimalFormat = DecimalFormat("$ #,###.##", DecimalFormatSymbols())
            val price: String = decimalFormat.format(searchResultItem.price)

            binding.textViewItemPrice.text = price
            Picasso.get().load(searchResultItem.thumbnail?.replace("http:", "https:"))
                .error(R.drawable.ic_launcher_background).into(binding.imageViewItem)

            binding.cardViewItem.setOnClickListener {
                listener.onClickSearchResult(searchResultItem)
            }
        }
    }

    interface SearchResultListener {
        fun onClickSearchResult(searchResultItem: SearchResultItem)
    }

}