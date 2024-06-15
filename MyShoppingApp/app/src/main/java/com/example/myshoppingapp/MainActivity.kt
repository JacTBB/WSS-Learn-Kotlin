package com.example.myshoppingapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myshoppingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val SHOP_ACTIVITY_RESULT_CODE=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(View.OnClickListener {
            var myIntent = Intent(this, ShopActivity::class.java)
            myIntent.putExtra("username", binding.etUserName.text.toString())
            startActivityForResult(myIntent, SHOP_ACTIVITY_RESULT_CODE)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            var myIntent = Intent(this, AboutActivity::class.java)
            startActivity(myIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SHOP_ACTIVITY_RESULT_CODE) {
            var recentActivity = data?.getStringExtra(ShopActivity.RETURN_MSG) as String
            binding.tvRecentActivity2.text = recentActivity
        }
    }
}