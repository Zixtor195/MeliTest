package com.example.melitest.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.melitest.R
import com.example.melitest.databinding.FragmentDetailsItemBinding
import com.example.melitest.ui.viewmodels.FragmentDetailsItemViewModel
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class FragmentDetailsItem : Fragment() {


    private val viewModelFragment: FragmentDetailsItemViewModel by viewModels()
    private lateinit var binding: FragmentDetailsItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        SetupUI(arguments)



        super.onViewCreated(view, savedInstanceState)
    }



    /** get args and Setup UI **/

    private fun SetupUI(arguments: Bundle?) {
        val titleArgs = arguments?.let { FragmentDetailsItemArgs.fromBundle(it).item.title }
        val priceArgs = arguments?.let { FragmentDetailsItemArgs.fromBundle(it).item.price }
        val thumbnailArgs = arguments?.let { FragmentDetailsItemArgs.fromBundle(it).item.thumbnail }


        binding.textViewItemDetailsTitle.text = titleArgs

        val decimalFormat = DecimalFormat("$ #,###.##", DecimalFormatSymbols())
        val price: String = decimalFormat.format(priceArgs)

        binding.textViewItemDetailsPrice.text = price
        Picasso.get().load(thumbnailArgs?.replace("http:", "https:"))
            .error(R.drawable.ic_launcher_background).into(binding.appCompatImageView)
    }




}