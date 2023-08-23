package com.puj.countries_information.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.puj.countries_information.databinding.ActivityCountryBinding
import com.squareup.picasso.Picasso

class CountryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCountryBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Populate the TextViews with the data from the intent
        binding.countryName.text = intent.getStringExtra("name")
        binding.countryCode.text = intent.getStringExtra("code")
        binding.Region.text = intent.getStringExtra("region")
        binding.subRegion.text = intent.getStringExtra("subregion")
        binding.currency.text = intent.getStringExtra("currency")
        binding.area.text = intent.getStringExtra("area")
        binding.numeriCode.text = intent.getStringExtra("numeric")

        // Load the country photo using Picasso or Glide
        Picasso.get().load(intent.getStringExtra("flag")).into(binding.countryPhoto)
    }
}