package com.nyp.sit.mydbapplicationstudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nyp.sit.mydbapplicationstudent.databinding.ActivityAddContactBinding
import com.nyp.sit.mydbapplicationstudent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_add_contact)

        //TODO 13 : Add listener to add contact into database

        binding.addRecordsBtn.setOnClickListener {
            val mc = MyContacts.ourInstance
            val addNameStr = addNameET.text.toString()
            val addNumberStr = addNumET.text.toString()
            mc.addToDatabase(addNameStr, addNumberStr, applicationContext)
            finish()
        }
    }
}