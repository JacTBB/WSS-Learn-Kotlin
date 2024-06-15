package com.example.mysharedpreferences

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mysharedpreferences.databinding.ActivityMainBinding
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadSavedPreferencdes()
        binding.button1.setOnClickListener(View.OnClickListener {
            if (binding.radioButton1.isChecked()) {
                savePreferences("gender", true)
            }
            else {
                savePreferences("gender", false)
            }
            savePreferences("Name", binding.editText1.getText().toString())
            finish()
        })
    }

    private fun loadSavedPreferencdes() {
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val cbValue = sp.getBoolean("gender", false)
        val name = sp.getString("Name", "YourName")
        if (cbValue) {
            binding.radioButton1.setChecked(true)
            binding.radioButton2.setChecked(false)
        }
        else {
            binding.radioButton1.setChecked(false)
            binding.radioButton2.setChecked(true)
        }
        binding.editText1.setText(name)
    }

    private fun savePreferences(key: String, value: Boolean) {
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val edit = sp.edit()
        edit.putBoolean(key, value)
        edit.commit()
    }
    private fun savePreferences(key: String, value: String) {
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val edit = sp.edit()
        edit.putString(key, value)
        edit.commit()
    }
}