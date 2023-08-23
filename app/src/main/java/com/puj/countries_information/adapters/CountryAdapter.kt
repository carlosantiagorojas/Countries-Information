package com.puj.countries_information.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.puj.countries_information.activities.CountryActivity
import com.puj.countries_information.classes.Country
import com.puj.countries_information.databinding.AdapterCountryBinding
import com.squareup.picasso.Picasso

class CountryAdapter(private val context: Context, private val countries: List<Country>) : BaseAdapter() {

    /**
     * Get the number of countries
     */
    override fun getCount(): Int {
        return countries.size
    }

    /**
     * Get the country at the specified position
     * @param position Position of the country
     */
    override fun getItem(position: Int): Any {
        return countries[position]
    }

    /**
     * Get the id of the country at the specified position
     * @param position Position of the country
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Get the view of the country at the specified position
     * @param position Position of the country
     * @param convertView View of the country
     * @param parent Parent view
     * @return View of the country
     */
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Get the country at the specified position
        val country = countries[position]
        // Inflate the view
        val binding = AdapterCountryBinding.inflate(LayoutInflater.from(context), parent, false)

        // Set the country information
        binding.countryName.text = country.Name
        binding.nativeName.text = country.NativeName
        binding.countryCode.text = country.Alpha3Code
        binding.currencyName.text = country.CurrencyName
        binding.currencySymbol.text = country.CurrencySymbol

        // Load the country flag with Picasso library
        Picasso.get().load(country.FlagPng).into(binding.countryPhoto)

        // Redirect to the phone app and show the country numeric code
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${country.NumericCode}")
            // Start the activity
            context.startActivity(intent)
        }

        // Intent to new activity when the user clicks on the card
        binding.cardCountries.setOnClickListener {
            // Create the intent
            val intent = Intent(context, CountryActivity::class.java)
            // Get the selected country
            val selectedCountry = countries[position]
            // Put the country information in the intent with the corresponding format
            intent.putExtra("name",selectedCountry.Name)
            intent.putExtra("code","${selectedCountry.Alpha2Code} - ${selectedCountry.Alpha3Code}")
            intent.putExtra("region",selectedCountry.Region)
            intent.putExtra("subregion","${selectedCountry.SubRegion} (${selectedCountry.Latitude}, ${selectedCountry.Longitude})")
            intent.putExtra("currency","(${selectedCountry.CurrencyCode}) - ${selectedCountry.CurrencySymbol}")
            intent.putExtra("area","Area: ${selectedCountry.Area}")
            intent.putExtra("numeric","NumericCode: ${selectedCountry.NumericCode}")
            intent.putExtra("flag",selectedCountry.FlagPng)
            // Start the activity
            context.startActivity(intent)
        }

        // Return the view
        return binding.root
    }
}