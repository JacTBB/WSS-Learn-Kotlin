package com.example.mycoroutineapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mycoroutineapp.databinding.ActivityMainBinding
import com.example.mycoroutineapp.util.BackEndJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    var j: Job? = null

    fun runBtn(v: View) {
        Log.d(this.javaClass.name, "Run button function")

        //TODO : create coroutine scope using default dispatcher
        var routineScope = CoroutineScope(Job() + Dispatchers.Default)

        //TODO : Launch coroutine
        routineScope.launch {
            withContext(Dispatchers.Main) {
                binding.tvDisplay.text = "Retrieving names"
            }

            var nameList = BackEndJob.getNames()

            withContext(Dispatchers.Main) {
                var msg = ""
                for (name in nameList) {
                    msg = msg.plus("$name\n")
                }
                binding.tvDisplay.text = msg
            }
        }

        Log.d(this.javaClass.name, "Run button function ended")
    }


    fun runBtnUsingFunction(v: View) {
        var routineScope = CoroutineScope(Job() + Dispatchers.Main)

        routineScope.launch {
            binding.tvDisplay.text = "Retrieving names"
            var nameList = getNames()
            var msg = ""
            for (name in nameList) {
                msg = msg.plus("$name\n")
            }
            binding.tvDisplay.text = msg
        }
    }

    //TODO : Create getNames suspend function to retrieve names
    suspend fun getNames(): List<String> = withContext(Dispatchers.Main) {
        BackEndJob.getNames()
    }
}
