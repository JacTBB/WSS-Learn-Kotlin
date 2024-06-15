package com.nyp.sit.mydbapplicationstudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.nyp.sit.mydbapplicationstudent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var myDB:MyDBAdapter? = null
    var contactsAdapter: ArrayAdapter<String>? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        registerForContextMenu(contactsLV)
    }

    private fun toggleVisibility() {
        if (contactsLV.count > 0) {
            binding.noitemsTV.visibility = View.GONE
            binding.contactsLV.visibility = View.VISIBLE
        } else {
            binding.contactsLV.visibility = View.GONE
            binding.noitemsTV.visibility = View.VISIBLE
        }
    }

    //TODO 10 : Add retreiveContacts method

    private fun retrieveContacts() {
        val contactList: List<String>
        val mc = MyContacts.ourInstance
        contactList = mc.retrieveAll(applicationContext)
        contactsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList)
        contactsLV.adapter = contactsAdapter
    }

    override fun onResume() {
        //TODO 12 : call retrieveContacts();
        retrieveContacts()
        toggleVisibility()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_add) {
            val myIntent = Intent(this, AddContactActivity::class.java)
            startActivity(myIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View,
                                     menuInfo: ContextMenu.ContextMenuInfo) {

        if (v.id == R.id.contactsLV) {
            menu.add(1, 0, 0, "Remove")
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    //TODO 11 :  - add onContextItemSelected method
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val mc = MyContacts.ourInstance
        mc.deleteFrmDatabase(info.position, applicationContext)
        retrieveContacts()
        toggleVisibility()
        return super.onContextItemSelected(item)
    }
}