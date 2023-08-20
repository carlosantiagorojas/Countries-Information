package com.puj.countries_information.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.puj.countries_information.adapters.CountryAdapter
import com.puj.countries_information.classes.Country
import com.puj.countries_information.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var CountriesList = ArrayList<Country>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitCountries()
    }

    private fun loadJSON(name: String): JSONObject {
        return try {
            val inputStream = assets.open(name)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            JSONObject(String(buffer, charset("UTF-8")))
        } catch (ex: org.json.JSONException) {
            ex.printStackTrace()
            JSONObject()
        }
    }

    private fun InitCountries() {
        val json = loadJSON("paises.json")
        val paisesJSON = json.getJSONArray("Countries")

        for (i in 0 until paisesJSON.length()) {
            val countryJson = paisesJSON.getJSONObject(i)

            val area = countryJson.optInt("Area", 0) // Handle missing or null values
            val numericCode = countryJson.optInt("NumericCode", 0) // Handle missing or null values

            val country = Country(
                countryJson.getString("Name"),
                countryJson.getString("Alpha2Code"),
                countryJson.getString("Alpha3Code"),
                countryJson.getString("NativeName"),
                countryJson.getString("Region"),
                countryJson.getString("SubRegion"),
                countryJson.getString("Latitude"),
                countryJson.getString("Longitude"),
                Area = area,
                NumericCode = numericCode,
                countryJson.getString("NativeLanguage"),
                countryJson.getString("CurrencyCode"),
                countryJson.getString("CurrencyName"),
                countryJson.getString("CurrencySymbol"),
                countryJson.getString("Flag"),
                countryJson.getString("FlagPng")
            )

            CountriesList.add(country)
        }

        val adapter = CountryAdapter(this, CountriesList )
        binding.countriesList.adapter = adapter

    }
}