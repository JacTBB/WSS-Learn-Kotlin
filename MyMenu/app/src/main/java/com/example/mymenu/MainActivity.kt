package com.example.mymenu

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mymenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerForContextMenu(binding.tvDemo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miRefresh) {
            binding.tvDemo.text = "Refreshed"
            Toast.makeText(this,"Refresh", Toast.LENGTH_SHORT).show()
        }
        else if (item.itemId == R.id.miLogoff) {
            binding.tvDemo.text = "Logged off"
            Toast.makeText(this,"Logged off", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.tvDemo) {
            menu?.add(1,1001,1,"Goodbye")
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == 1001) {
            finish()
        }
        return super.onContextItemSelected(item)
    }
}