package com.example.myfragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() ,FragmentBDemo.FragmentBButtonListener {
    var fragmentManager: FragmentManager? = null

    var sum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alternate_fragment_layout)

        fragmentManager = supportFragmentManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        if (item.itemId == R.id.quitMenuItem) {
            AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Close Session").setMessage("Are you sure?")
                .setPositiveButton("Yes", object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        finish()
                    }
                })
                .setNegativeButton("Cancel", object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.cancel()
                    }
                }).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun runFragmentA(v: View) {
        val fragment = FragmentADemo.newInstance()
        val frag_transaction = fragmentManager?.beginTransaction()
        frag_transaction?.replace(R.id.fragment_container, fragment, "frag_A")
        frag_transaction?.addToBackStack(null)
        frag_transaction?.commit()
    }

    fun runFragmentB(v: View) {
        val fragment = FragmentBDemo.newInstance()
        val frag_transaction = fragmentManager?.beginTransaction()
        frag_transaction?.replace(R.id.fragment_container, fragment, "frag_B")
        frag_transaction?.addToBackStack(null)
        frag_transaction?.commit()
    }

    fun runToggle(v: View) {
        var fragment = supportFragmentManager.findFragmentByTag("frag_A")
        if (fragment != null && fragment is FragmentADemo) {
            (fragment as FragmentADemo).toggleSumDisplayVisibility()
        }
        else {
            if (fragment == null) {
                Toast.makeText(this, "Fragment is null", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Fragment is not fragment A!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onButtonClickListener(someNumber: Int) {
        val oldSum = sum
        sum += someNumber

        val msg = "Adding $someNumber to $oldSum gives you $sum"
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()

        DialogShowFragment.newInstance(msg).show(supportFragmentManager, "DialogDisplayMsg")
    }
}