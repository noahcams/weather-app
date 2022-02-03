package com.example.weatherapp.model

import com.example.weatherapp.model.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object WeatherRepo {

    private val weatherService by lazy { RetrofitInstance().weatherService }
    const val CHELSEA_ID = "4054852"
    const val CHELSEA = "chelsea"
/*
    var cityIdMap = hashMapOf(
        "calera" to "4053050",
        "villas" to "4047914",
        "florence" to "4062577",
        "brookhighland" to "4051894",
        "decatur" to "4058553",
        "cullman" to "4057835",
        "bessemer" to "4048023",
        "fairhope" to "4061234",
        "demopolis" to "4058662",
        "lugoff" to "4050356",
        "paducah" to "4048662",
        "enterprise" to "4060791",
        "forthunt" to "4046704",
        "brewton" to "4051714",
        "daphne" to "4058219",
        "chelsea" to "4054852",
        "birmingham" to "4049979",
        "eufaula" to "4060954",
        "childersburg" to "4055045",
        "boaz" to "4051059",
        "centerpoint" to "4054378",
        "redchute" to "4048888",
        "foley" to "4062644",
        "Fairfield" to "4061206",
        "henderson" to "4046332",
        "danville" to "4058198",
        "edna" to "4046274",
        "delhihills" to "4050006",
        "bayminette" to "4046255",
        "jessup" to "4049032",
        "clanton" to "4055577",
        "daleville" to "4058061",
        "trinity" to "4047906",
        "moseisley" to "1",
        "chickasaw" to "4054996",
        "eastflorence" to "4059870",
        "clay" to "4055650",
        "buda" to "4050880",
        "dothan" to "4059102",
        "turpinhills" to "4050118",
        "cahabaheights" to "4052964",
    )
*/
    suspend fun getCity(cityName: String): CityData = withContext(Dispatchers.IO) {
        weatherService.getCity(cityName)
    }

    suspend fun getWeather(cityId: String): City = withContext(Dispatchers.IO) {
        weatherService.getWeather(cityId)
    }

    suspend fun getRadar(cityId: String): ImageURLs = withContext(Dispatchers.IO) {
        weatherService.getRadar(cityId)
    }
}