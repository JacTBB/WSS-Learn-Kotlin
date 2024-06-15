package it2161.myroomapplicationstudent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it2161.myroomapplicationstudent.databinding.ActivityAddContactBinding
import it2161.myroomapplicationstudent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_add_contact)


        addRecordsBtn.setOnClickListener {
            val addNameStr = binding.addNameET.text.toString()
            val addNumberStr = binding.addNumET.text.toString()
            val addAddressStr = binding.addAddressET.text.toString()
            val addNotesStr = binding.addNotesET.text.toString()

            val data = Intent()
            data.putExtra("name",addNameStr)
            data.putExtra("num",addNumberStr)
            data.putExtra("address",addAddressStr)
            data.putExtra("notes",addNotesStr)
            setResult(Activity.RESULT_OK,data)

            finish()

        }

    }
}
