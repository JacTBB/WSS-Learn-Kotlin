package com.example.myshoppingapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myshoppingapp.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopBinding

    companion object {
        val RETURN_MSG = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userNameFromMain = intent.getStringExtra("username")
        binding.tvWelcome.text = "Welcome $userNameFromMain"

        binding.btnLogout.setOnClickListener(View.OnClickListener {
            var output = Intent()
            output.putExtra(RETURN_MSG, "You have bought ${binding.etQuan.text.toString()} Android Phones")
            setResult(Activity.RESULT_OK, output)
            finish()
        })
    }
}