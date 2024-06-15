package com.example.heros

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.heros.databinding.ActivityMainBinding
import com.example.heros.databinding.ActivityHerosCardBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var comicTypeArray : Array<String>?=null
    var dcHerosArray = arrayListOf("Batman","Superman","WonderWoman","Flash","Aquaman")
    var marvelHerosArray = arrayListOf("IronMan","Thor","BlackWidow","Spiderman","Black Panther")
    var arrayHerosAdapter : CustListAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1: Convert comic_type string array resource to an String Array and assign it to comicTypeArray in MainActivity
        comicTypeArray = resources.getStringArray(R.array.comic_type)

        // 2: Create adapter and bind with comicTypeArray
        val arrayComicTypeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            comicTypeArray!!
        )

        // 3: Set drop down view resource
        arrayComicTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // 4: Assign adapter to spinComicType
        binding.spinComicType.adapter = arrayComicTypeAdapter

        // 9: set onItemSelectedListener for Spinner spinComicType
        // 10: Change data for arrayHerosAdapter to reflect user selection in spinComicType
        binding.spinComicType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                arrayHerosAdapter?.clear()

                if (comicTypeArray?.get(position)=="DC") {
                    arrayHerosAdapter?.addAll(dcHerosArray)
                }
                else {
                    arrayHerosAdapter?.addAll(marvelHerosArray)
                }

                arrayHerosAdapter?.notifyDataSetChanged()
            }
        }

        // 5:create Adapter for arrayHerosAdapter
        arrayHerosAdapter = CustListAdapter(this, dcHerosArray)

        // 6:Assign adapter to ListView listHero
        binding.listHero.adapter = arrayHerosAdapter

        // 7:Set onItemClickListener for listHero.
        // 8: Show a toast that displays the item that user selected
        binding.listHero.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                displayToast("You have selected ${parent?.adapter?.getItem(position)}")
            }
        }

        // Display HerosCardActivity
        val intent = Intent(this, HerosCardActivity::class.java)
        startActivity(intent)
    }

    fun displayToast(message:String){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 2.1:Inflat the menu resource called main
        // 2.2:Inside main.xml add the menu item refresh and settings
        // 2.3:Ensure refresh is always availabe in the app bar
        // 2.4:Change the menu item settings to have a submenu and an menu item called logoff
        menuInflater.inflate(R.menu.main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var msg = ""

        // 2.5:Display "Refreshing ..." when user selects refresh and "Are you sure you wish to logoff?" when logoff is selected
        when(item.itemId) {
            R.id.menuitem_logoff -> msg = "Are you sure you wish to logoff?"
            R.id.menuitem_refresh -> msg = "Refreshing..."
        }

        displayToast(msg)

        return super.onOptionsItemSelected(item)
    }

    // 3.1: Update CustListAdapter to extend BaseAdapter()
    // 3.4: During init, assign the LayoutInflater and add all the entries from data to sList.
    // 3.5: override getView and retrieve the entry in position p0 from sList and bind it to the view
    // 3.6: Ensure arrayHerosAdapter uses CustListAdapter of ArrayAdapter
    class CustListAdapter(context: Context, data:ArrayList<String>):BaseAdapter() {
        private val sList:ArrayList<String> = ArrayList<String>()

        // 3.2: Uncomment away the below statement
        private val mInflater : LayoutInflater

        init {
            this.mInflater = LayoutInflater.from(context)
            sList.addAll(data)
        }

        fun addAll(data: Collection<String>){
            sList.addAll(data)
        }

        fun clear(){
            sList.clear()
        }

        // 3.3 Uncomment getItem,getItemId and getCount
        override fun getItem(p0: Int): Any {
            return sList[p0]
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return sList.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val v: View = mInflater.inflate(R.layout.list_item, parent, false)

            val label = v.findViewById<TextView>(R.id.heroname)
            label.text = sList[position]

            val iv = v.findViewById<ImageView>(R.id.heroimage)
            iv.setImageResource(R.drawable.defaulthero)

            return v
        }
    }
}
