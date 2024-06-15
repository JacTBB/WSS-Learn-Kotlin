package com.example.myfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text

var sumDisplay: TextView?=null

class FragmentADemo : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_a_demo, container, false)
        sumDisplay = v.findViewById<TextView>(R.id.sumDisplayTV)
        sumDisplay?.visibility = View.INVISIBLE
        var sum = (activity as MainActivity).sum
        sumDisplay?.text = "Curerent Sum is $sum"
        return v
    }

    companion object {
        fun newInstance() = FragmentADemo()
    }

    fun toggleSumDisplayVisibility() {
        if (sumDisplay?.visibility == View.VISIBLE) {
            sumDisplay?.visibility = View.INVISIBLE
        }
        else {
            sumDisplay?.visibility = View.VISIBLE
        }
    }
}