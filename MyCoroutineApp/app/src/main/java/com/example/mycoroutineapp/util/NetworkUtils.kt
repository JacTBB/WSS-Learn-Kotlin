package com.example.mycoroutineapp.util

import android.net.Uri
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.Scanner

class NetworkUtils() {


    companion object {
        private val TAG: String = NetworkUtils::class.java!!.simpleName

        private val JSON_RESPONSE_URL = "https://jsonplaceholder.typicode.com"

        private val TYPE_SINGLE = 1

        private val TYPE_ALL = 0


        /**
         * Builds the URL used to talk to the weather server using a location. This location is based
         * on the query capabilities of the weather provider that we are using.
         *
         * @param locationQuery The location that will be queried for.
         * @return The URL to use to query the weather server.
         */

        fun buildURLSingleType(id: Int): URL {

            return buildUrl(
                TYPE_SINGLE,
                id
            )
        }

        fun buildURLAll(): URL {

            return buildUrl(
                TYPE_ALL,
                0
            )

        }

        private fun buildUrl(type: Int, id: Int): URL {
            //TODO 1 : Complete the URL to retrieve weather information.

            val resourceType = "posts"

            var uri = Uri.parse(JSON_RESPONSE_URL).buildUpon()
                .appendPath(resourceType)

            if (type == TYPE_SINGLE) {
                uri.appendPath("1").build()
            }

            val builtUri = uri.build()


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
            } catch (ex: Exception) {
                Log.v(TAG, "URL Error")
                Log.d(TAG, ex.toString())


            } finally {
                urlConnection.disconnect()
            }

            return null
        }
    }
}