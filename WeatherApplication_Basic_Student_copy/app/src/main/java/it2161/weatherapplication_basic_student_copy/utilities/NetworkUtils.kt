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


package it2161.weatherapplication_basic_student_copy.utilities

import android.app.Activity
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

/**
 * Remodified by keeliren on 5/3/18.
 */


class NetworkUtils{
    companion object {
        private val TAG: String= NetworkUtils::class.java.simpleName


        private val FORECASE_OPEN_WEATHER_URL= "https://api.openweathermap.org/data/2.5/forecast"


        /*
         * NOTE: These values only effect responses from OpenWeatherMap, NOT from the fake weather
         * server. They are simply here to allow us to teach you how to build a URL if you were to use
         * a real API.If you want to connect your app to OpenWeatherMap's API, feel free to! However,
         * we are not going to show you how to do so in this course.
         */

        /* The format we want our API to return */
        private val format = "json"
        /* The units we want our API to return */
        private val units = "metric"
        /* The number of days we want our API to return */
        private val numDays = 1

        val QUERY_PARAM = "q"
        val FORMAT_PARAM = "mode"
        val UNITS_PARAM = "units"
        val DAYS_PARAM = "cnt"
        val API_KEY="APPID"


        /**
         * Builds the URL used to talk to the weather server using a location. This location is based
         * on the query capabilities of the weather provider that we are using.
         *
         * @param locationQuery The location that will be queried for.
         * @return The URL to use to query the weather server.
         */
        fun buildUrl(locationQuery: String): URL {
            //TODO 1 : Complete the URL to retrieve weather information.
            val builtUri = Uri.parse(FORECASE_OPEN_WEATHER_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, locationQuery)
                .appendQueryParameter(FORMAT_PARAM, format)
                .appendQueryParameter(UNITS_PARAM, units)
                .appendQueryParameter(DAYS_PARAM, numDays.toString())
                .appendQueryParameter(API_KEY, "0e0d7490f406a855e2bcbfdf5b4cc0e8")
                .build()

            var url: URL? = null
            try {
                url = URL(builtUri.toString())
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }

            Log.v(TAG, "Built URI " + url!!)

            return url
        }


        /**
         * This method returns the entire result from the HTTP response.
         *
         * @param url The URL to fetch the HTTP response from.
         * @return The contents of the HTTP response.
         * @throws IOException Related to network and stream reading
         */

        @Throws(IOException::class)
        fun getResponseFromHttpUrl(url: URL): String? {
            val urlConnection = url.openConnection() as HttpURLConnection
            try {
                val `in` = urlConnection.getInputStream()

                val scanner = Scanner(`in`)
                scanner.useDelimiter("\\A")


                val hasInput = scanner.hasNext()
                return if (hasInput) {
                    scanner.next()
                } else {
                    null
                }
            }
            catch (ex : Exception)
            {

                Log.d("Weather App",ex.toString())


            }
            finally {
                urlConnection.disconnect()
            }

            return null
        }
    }
}