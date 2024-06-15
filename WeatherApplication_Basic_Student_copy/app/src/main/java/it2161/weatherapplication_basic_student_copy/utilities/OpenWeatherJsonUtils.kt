package it2161.weatherapplication_basic_student_copy.utilities

import android.content.ContentValues
import android.content.Context
import it2161.weatherapplication_basic_student_copy.entity.WeatherEntry
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection


/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Remodified by keeliren on 5/3/18.
 */

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */

class OpenWeatherJsonUtils {

    companion object {


        /**
         * This method parses JSON from a web response and returns an array of Strings
         * describing the weather over various days from the forecast.
         *
         *
         * Later on, we'll be parsing the JSON into structured data within the
         * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
         * now, we just convert the JSON into human-readable strings.
         *
         * @param forecastJsonStr JSON response from server
         *
         * @return Array of WeatherEntry describing weather data
         *
         * @throws JSONException If JSON data cannot be properly parsed
         */
        @Throws(JSONException::class)
        fun getSimpleWeatherStringsFromJson(
            context: Context,
            forecastJsonStr: String
        ): List<WeatherEntry>? {

            /* Weather information. Each day's forecast info is an element of the "list" array */
            val OWM_LIST = "list"

            val OWM_PRESSURE = "pressure"
            val OWM_HUMIDITY = "humidity"
            val OWM_WINDSPEED = "speed"
            val OWM_WIND_DIRECTION = "deg"

            /* All temperatures are children of the "temp" object */
            val OWM_TEMPERATURE = "main"

            /* Max temperature for the day */
            val OWM_MAX = "temp_max"
            val OWM_MIN = "temp_min"

            val OWM_WEATHER = "weather"
            val OWM_DESCRIPTION = "main"
            val OWM_WEATHER_ID = "id"
            val OWM_MESSAGE_CODE = "cod"

            /* String array to hold each day's weather data */
            var parsedWeatherData = mutableListOf<WeatherEntry>()

            val localDate = System.currentTimeMillis()
            val utcDate = OpenWeatherDateUtils.getUTCDateFromLocal(localDate)
            val startDay = OpenWeatherDateUtils.normalizeDate(utcDate)

            var dateTimeMillis = startDay + OpenWeatherDateUtils.DAY_IN_MILLIS

            var date = OpenWeatherDateUtils.getFriendlyDateString(context, dateTimeMillis, false)


            //TODO 2 : Complete the codes to read the different values from the JSON response
            val forecastJson = JSONObject(forecastJsonStr)

            if (forecastJson.has(OWM_MESSAGE_CODE)) {
                val errorCode = forecastJson.getInt(OWM_MESSAGE_CODE)

                when (errorCode) {
                    HttpURLConnection.HTTP_OK -> {}
                    HttpURLConnection.HTTP_NOT_FOUND -> return null
                    else -> return null
                }
            }

            val weatherArray = forecastJson.getJSONArray(OWM_LIST)

            for (i in 0 until weatherArray.length()) {
                val pressure: Float
                val humidity: Float
                val windSpeed: Float
                val windDirection: Float
                val high: Double
                val low: Double
                val weatherId: Int
                val description: String

                val dayForecast = weatherArray.getJSONObject(i)
                val weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0)
                weatherId = weatherObject.getInt(OWM_WEATHER_ID)

                val temperaturObject = dayForecast.getJSONObject("main")
                high = temperaturObject.getDouble(OWM_MAX)
                low = temperaturObject.getDouble(OWM_MIN)
                pressure = temperaturObject.getDouble(OWM_PRESSURE).toFloat()
                humidity = temperaturObject.getInt(OWM_HUMIDITY).toFloat()

                val windObject = dayForecast.getJSONObject("wind")
                windSpeed = windObject.getDouble(OWM_WINDSPEED).toFloat()
                windDirection = windObject.getDouble(OWM_WIND_DIRECTION).toFloat()

                parsedWeatherData.add(WeatherEntry(date, weatherId, high, low, humidity, pressure, windSpeed, windDirection))
            }

            return parsedWeatherData
        }

        /**
         * Parse the JSON and convert it into ContentValues that can be inserted into our database.
         *
         * @param context         An application context, such as a service or activity context.
         * @param forecastJsonStr The JSON to parse into ContentValues.
         *
         * @return An array of ContentValues parsed from the JSON.
         */
        fun getFullWeatherDataFromJson(
            context: Context,
            forecastJsonStr: String
        ): Array<ContentValues>? {
            /** This will be implemented in a future lesson  */
            return null
        }
    }
}