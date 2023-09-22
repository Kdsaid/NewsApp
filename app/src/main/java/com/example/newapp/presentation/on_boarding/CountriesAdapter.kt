package com.example.newapp.presentation.on_boarding

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.newapp.R
import com.example.newapp.data.local.Country
import com.example.newapp.databinding.CountryListItemBinding
import com.example.newapp.presentation.common.CustomRecyclerViewAdapter

class CountriesAdapter(
    private val itemClick: (String) -> Unit,
) : CustomRecyclerViewAdapter<Country>() {
    private var selectedItemPosition = -1
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return CountryListItemBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_list_item, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun bind(binding: ViewBinding, position: Int) {
        val country = getItem(position)
        val countryListItemBinding = binding as CountryListItemBinding

        with(countryListItemBinding) {
            country?.let {

                countryName.text = country.countryName
                countryCard.apply {

                    setOnClickListener {
                        itemClick(country.code)
                        selectedItemPosition = position
                        notifyDataSetChanged()
                    }

                    if (selectedItemPosition == position) {
                        countryCard.strokeWidth = 4
                    } else {
                        countryCard.strokeWidth = 0
                    }
                }


            }
        }


    }

    fun setData(newData: List<Country>) {
        submitList(newData)
    }
}
