package com.example.heros

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heros.databinding.ActivityHerosCardBinding

class HerosCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHerosCardBinding

    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHerosCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dcHerosNameArray = arrayListOf("Batman", "Superman", "WonderWoman", "Flash", "Aquaman")
        var dcHerosArray = arrayListOf<Hero>()

        //TODO 4.5: Populate dcHerosArray with Hero object created by dcHerosNameArray and available resources
        for (i in 0..4) {
            dcHerosArray.add(Hero(dcHerosNameArray[i], resources.getDrawable(R.drawable.defaulthero)))
        }

        //TODO 4.6: Create HerosRecycleViewAdapter and assign to herosRecyclerView adapter
        binding.herosRecyclerView.adapter = HerosRecycleViewAdapter(dcHerosArray, this)

        //TODO 4.7: Assign LearnLayoutManager to herosRecyclerView's layout manager
        layoutManager = LinearLayoutManager(this)
        binding.herosRecyclerView.layoutManager = layoutManager
    }

    //TODO 4.1: Update HerosRecycleViewAdapter to extend RecyclerView.Adapter
    class HerosRecycleViewAdapter(val items: ArrayList<Hero>, val context: Context): RecyclerView.Adapter<HerosRecycleViewAdapter.HerosViewHolder>()
    {
        //TODO 4.1: Override onCreateViewHolder, getItemCount,onBindViewHolder
        //TODO 4.2: Update onCreateViewHolder to return HerosViewHolder
        //TODO 4.3: Update getITemCount to return items.size
        //TODO 4.4: Update onBindViewHolder to update holder with the Hero object in items array at the provided position

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerosViewHolder {
            return HerosViewHolder(LayoutInflater.from(context).inflate(R.layout.heroscardview, parent, false))
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: HerosViewHolder, position: Int) {
            holder.updateHero(items[position])
        }

        class HerosViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val heroname = v.findViewById<TextView>(R.id.title)
            val heroimage = v.findViewById<ImageView>(R.id.heroimage)

            fun updateHero(hero: Hero) {
                heroimage.setImageDrawable(hero.heroImage)
                heroname.text = hero.heroName
            }
        }
    }
}