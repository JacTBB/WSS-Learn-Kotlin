package com.example.mycoroutineapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mycoroutineapp.databinding.ActivityBasicCoroutineBinding
import com.example.mycoroutineapp.util.BackEndJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Basic_Coroutine_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityBasicCoroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var it = menu?.add(0,1001,1,"Clear")
        it?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 1001){
            binding.tvMainDisplay.text = ""
            binding.tvCoroutineDisplay.text = ""
        }

        return super.onOptionsItemSelected(item)
    }

    fun RunMainTask(v : View){
        Log.d("xCoroutinex","I'm working in thread ${Thread.currentThread().name}")
        var msg = ""
        var nameList = BackEndJob.getNames()

        for (name in nameList) {
            msg = msg.plus("$name\n")
        }

        binding.tvMainDisplay.text = msg
    }

    fun RunCoroutine(v : View){
        Log.d("xCoroutinex","Invoking RunCoroutine function in thread ${Thread.currentThread().name}")
        //TODO : Create a coroutine that runs using default dispatcher on a globalscope
        //TODO : Assign job created to a variable, nameJob
        //TODO : With the returned name list, create a string that holds all the names in the list
        var nameJob = GlobalScope.async(Dispatchers.Default) {
            var msg = ""
            var nameList = BackEndJob.getNames()

            for (name in nameList) {
                msg = msg.plus("$name\n")
            }
            msg
        }

        Log.d("xCoroutinex","Updating tvCoroutineDisplay in ${Thread.currentThread().name}")
        binding.tvCoroutineDisplay.text = "Lets wait for the name.."
        Log.d("xCoroutinex","Launching coroutine using Main dispatcher in thread ${Thread.currentThread().name}")
        //TODO : Launch a globalscope coroutine using Main dispatcher
        //TODO : Wait for nameJob to complete and update tvCoroutineDisplay with the returned string.
        GlobalScope.launch(Dispatchers.Main) {
            binding.tvCoroutineDisplay.text = nameJob.await()
        }
    }
}
