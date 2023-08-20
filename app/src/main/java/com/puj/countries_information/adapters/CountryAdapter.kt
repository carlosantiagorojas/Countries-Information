package com.puj.countries_information.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.puj.countries_information.classes.Country
import com.puj.countries_information.databinding.AdapterCountryBinding
import com.squareup.picasso.Picasso

class CountryAdapter(private val context: Context, private val countries: List<Country>) : BaseAdapter() {

    override fun getCount(): Int {
        return countries.size
    }

    override fun getItem(position: Int): Any {
        return countries[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val country = countries[position]
        val inflater = LayoutInflater.from(context)
        val binding = AdapterCountryBinding.inflate(inflater, parent, false)

        binding.countryName.text = country.Name
        binding.nativeName.text = country.NativeName
        binding.countryCode.text = country.Alpha3Code
        binding.currencyName.text = country.CurrencyName
        binding.currencySymbol.text = country.CurrencySymbol

        // You can also load images using a library like Picasso or Glide
        Picasso.get().load(country.FlagPng).into(binding.countryPhoto)

        return binding.root
    }
}