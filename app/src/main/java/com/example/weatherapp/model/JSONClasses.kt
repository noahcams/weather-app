package com.example.weatherapp.model

data class City(
    val city: CityX,
    val weather: Weather
)

data class CityX(
    val admin1Code: String,
    val admin2Code: Int,
    val admin3Code: Any,
    val admin4Code: String,
    val alternatenames: String,
    val asciiname: String,
    val cc2: String,
    val countryCode: String,
    val dem: Int,
    val elevation: Int,
    val featureClass: String,
    val featureCode: String,
    val geonameid: Int,
    val imageURLs: ImageURLs,
    val latitude: Double,
    val longitude: Double,
    val modificationDate: String,
    val name: String,
    val population: Int,
    val timezone: String
)

data class Weather(
    val days: List<Day>,
    val id: Int
)

data class ImageURLs(
    val androidImageURLs: AndroidImageURLs,
    val iOSImageURLs: IOSImageURLs
)

data class AndroidImageURLs(
    val hdpiImageURL: String,
    val mdpiImageURL: String,
    val xhdpiImageURL: String
)

data class IOSImageURLs(
    val imageURL: String
)

data class Day(
    val dayOfTheWeek: Int,
    val high: Int,
    val hourlyWeather: List<HourlyWeather>,
    val low: Int,
    val weatherType: String
)

data class HourlyWeather(
    val hour: Int,
    val humidity: Double,
    val rainChance: Double,
    val temperature: Int,
    val weatherType: String,
    val windSpeed: Double
)

data class CityData(
    val cities: List<CityX>,
    val startIndex: Int,
    val totalCitiesFound: Int
)