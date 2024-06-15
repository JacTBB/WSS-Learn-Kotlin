package com.example.mycoroutineapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycoroutineapp.databinding.ActivityNetworkBinding
import com.example.mycoroutineapp.util.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class NetworkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNetworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()


        //TODO : Create coroutine scope using IO dispatcher
        //TODO : Run coroutine async to build URL and retrieve response with the built URL
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        var nwSingleItemJob = scope.async() {
            val nwURL = NetworkUtils.buildURLSingleType(1)
            val response = NetworkUtils.getResponseFromHttpUrl(nwURL)
            response
        }

        //TODO : Launch coroutine using default dispatcher
        //TODO : break down the json response and assign it to variables
        scope.launch(Dispatchers.Default) {
            val response = nwSingleItemJob.await()
            val jsonResponse = JSONObject(response)
            val userid = jsonResponse.getInt("userId")
            val id = jsonResponse.getInt("id")
            val title = jsonResponse.getString("title")
            val body = jsonResponse.getString("body")

            val msg = "UserID = $userid \n ID = $id \n Title = $title \n Body = $body"

            withContext(Dispatchers.Main) {
                binding.tvJSONBreakDownDisplay.text = msg
                binding.tvJSONDisplay.text = response
            }
        }

        //TODO : Run coroutine async to build URL which retrieves all items and retrieve response with the built URL
        var nwMultipleItemsJob = scope.async(Dispatchers.IO) {
            val nwURL = NetworkUtils.buildURLAll()
            val response = NetworkUtils.getResponseFromHttpUrl(nwURL)
            response
        }

        //TODO : Launch coroutine async using default dispatcher
        //TODO : Go thru the entire item list and break down the json response and assign it to variables
        scope.async(Dispatchers.Default) {
            val response = nwMultipleItemsJob.await()
            val jsonResponse = JSONArray(response)
            var msg = "$response\n\n"
            for (i in 0 until jsonResponse.length()) {
                val jsonItem = jsonResponse.getJSONObject(i)
                val userid = jsonItem.getInt("userId")
                val id = jsonItem.getInt("id")
                val title = jsonItem.getString("title")
                val body = jsonItem.getString("body")
                msg = msg + "item $i\n\nuser id = $userid\nid = $id\n title = $title\n body = $body\n\n"
            }

            withContext(Dispatchers.Main) {
                binding.tvJSONMultipleItemDisplay.text = msg
            }
        }
    }
}
