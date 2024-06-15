package com.example.layoutsandcontrols

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.RadioGroup
import android.text.InputType
import com.example.layoutsandcontrols.databinding.ActivityMainAdvancedBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainAdvancedBinding
    var loginType: String = "Student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAdvancedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            if (binding.loginET.text.toString().isEmpty()) {
                binding.loginET.error = "Login cannot be empty"
            }
            else if (binding.passwordET.text.toString().isEmpty()) {
                binding.passwordET.error = "Password cannot be empty"
            }
            else if (binding.passwordET.text.toString() == "password") {
                displayToast("$loginType Login: Success")
            }
            else {
                displayToast("$loginType Login: Error")
            }
        }

        binding.employeeTypeRG.setOnCheckedChangeListener(object:RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup, p1: Int) {
                if (p1 == R.id.rbtnStaff) {
                    loginType = "Staff"
                }
                else if (p1 == R.id.rbtnStudent) {
                    loginType = "Student"
                }
            }
        })

        binding.chkBoxShowPassword.setOnClickListener {
            if (binding.chkBoxShowPassword.isChecked == true) {
                binding.passwordET.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            else {
                binding.passwordET.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
            }
        }

        binding.fab.setOnClickListener {
            Snackbar.make(it, "Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }
    }

    private fun displayToast(message:String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}