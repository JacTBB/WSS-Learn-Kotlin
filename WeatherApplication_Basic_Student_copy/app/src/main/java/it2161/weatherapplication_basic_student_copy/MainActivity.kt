package it2161.weatherapplication_basic_student_copy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import it2161.weatherapplication_basic_student_copy.databinding.ActivityForecastBinding
import it2161.weatherapplication_basic_student_copy.utilities.NetworkUtils
import it2161.weatherapplication_basic_student_copy.utilities.OpenWeatherJsonUtils
import it2161.weatherapplication_basic_student_copy.utilities.OpenWeatherUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForecastBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loadWeatherData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refreshMenuItem) {
            loadWeatherData()
        }
        else if (item.itemId == R.id.quitMenuItem) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadWeatherData() {
        val location = "Singapore"
        //TODO 3: Create a Coroutine and invoke it asynchronously to build the url using the location as argument. Assign the job to a weatherJob variable
        //TODO 4: Get a http response using location in the same Coroutine
        val weatherJob  = CoroutineScope(Job() + Dispatchers.IO).async() {
            val weatherReportUrl = NetworkUtils.buildUrl(location)

            try {
                val jsonWeatherResponse = NetworkUtils
                    .getResponseFromHttpUrl(weatherReportUrl)
                val responseList = OpenWeatherJsonUtils
                    .getSimpleWeatherStringsFromJson(this@MainActivity, jsonWeatherResponse!!)
                responseList
            }
            catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        //TODO 5 : Create a coroutine using the main dispatcher
        //TODO 6 : Wait for the response from the job created previously
        //TODO 7 : Make use of OpenWeatherUtils to breakdown the JSON response
        GlobalScope.launch(Dispatchers.Main) {
            val weatherData = weatherJob.await()
            if (weatherData != null) {
                for (weatherEntry in weatherData) {
                    val description = OpenWeatherUtils
                        .getStringForWeatherCondition(this@MainActivity, weatherEntry.weatherID)
                    val highString = OpenWeatherUtils
                        .formatTemperature(this@MainActivity, weatherEntry.tempMax)
                    val lowString = OpenWeatherUtils
                        .formatTemperature(this@MainActivity, weatherEntry.tempMin)
                    val humidityString = getString(R.string.format_humidity, weatherEntry.humidity)
                    val windString = OpenWeatherUtils
                        .getFormattedWind(this@MainActivity, weatherEntry.windspeed, weatherEntry.degrees)
                    val pressureString = getString(R.string.format_pressure, weatherEntry.pressure)
                    val date = weatherEntry.date
                    val weather_id = weatherEntry.weatherID
                    withContext(Dispatchers.Main) {
                        binding.weatherDescriptionTV.text = description
                        binding.weatherDateTV.text = date
                        binding.weatherTempHighTV.text = highString
                        binding.weatherTempLowTV.text = lowString
                        binding.weatherIV.setImageResource(OpenWeatherUtils
                            .getArtResourceForWeatherCondition(weather_id))
                        binding.weatherHumidityTV.text = humidityString
                        binding.weatherPressureTV.text = pressureString
                        binding.weatherWindSpeedTV.text = windString
                    }
                }
            }
        }
    }
}