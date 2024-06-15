package it2161.myroomapplicationstudent

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import it2161.myroomapplicationstudent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    var contactsAdapter: ArrayAdapter<String>? = null
    var allContacts: List<Contacts>? = null
    //TODO 16:
    //  - Create the ViewModel
    private val contactsViewModel: ContactsViewModel by viewModels() {
        ContactsViewModelFactory((application as MyContacts).repo)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)

        registerForContextMenu(binding.contactsLV)

        //TODO 17:
        //  - Add an observer for the allContacts LiveData from ContactViewModel
        contactsViewModel.allContacts.observe(this, Observer {
            var contactsConvertList = mutableListOf<String>()
            allContacts = it
            for (contact in it) {
//                contactsConvertList.add("${contact.name} (${contact.num})")
                contactsConvertList.add("${contact.name} (${contact.num} - ${contact.address}) - ${contact.notes}")
            }
            it?.let {
                contactsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsConvertList)
                contactsLV.adapter = contactsAdapter
            }
            toggleVisibility()
        })

    }

    private fun toggleVisibility() {
        if (binding.contactsLV.count > 0) {
            binding.noitemsTV.visibility = View.GONE
            binding.contactsLV.visibility = View.VISIBLE
        } else {
            binding.contactsLV.visibility = View.GONE
            binding.noitemsTV.visibility = View.VISIBLE
        }
    }


    private fun retrieveContacts() {


    }

    override fun onResume() {
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
            startActivityForResult(myIntent, 1)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu, v: View,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {

        if (v.id == R.id.contactsLV) {
            menu.add(1, 0, 0, "Remove")
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        //TODO 19:
        //  - Make use of view model to remove the selected contact.
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        if (allContacts != null) {
            val contact = allContacts!!.get(info.position)
            contactsViewModel.remove(contact)
            toggleVisibility()
        }
        return super.onContextItemSelected(item)
    }

    //TODO 18 :
    // - Implement onActivityResult to insert the new contact using the ViewModel after receiving a response from AddContactActivity.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (data != null) {
                    val name = data.getStringExtra("name")
                    val num = data.getStringExtra("num")
                    val address = data.getStringExtra("address")
                    val notes = data.getStringExtra("notes")
//                    contactsViewModel.insert(Contacts(0, name!!,num!!))
                    contactsViewModel.insert(Contacts(0, name!!,num!!,address!!,notes!!))
                }
            }
        }
    }
}
