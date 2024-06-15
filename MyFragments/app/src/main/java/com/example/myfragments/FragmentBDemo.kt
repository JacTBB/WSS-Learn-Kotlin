package com.example.myfragments

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import java.lang.RuntimeException

class FragmentBDemo : Fragment() {
    private var listener: FragmentBDemo.FragmentBButtonListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_b_demo, container, false)
        val fragmentBtn = v.findViewById<Button>(R.id.fragmentButton)
        val fragmentEt = v.findViewById<EditText>(R.id.fragmentET)
        fragmentBtn.setOnClickListener {
            val num = fragmentEt.text.toString().toInt()
            listener?.onButtonClickListener(num)
        }

        return v
    }

    companion object {
        fun newInstance() = FragmentBDemo()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentBButtonListener) {
            listener = context
        }
        else {
            throw RuntimeException("$context must implement OnFragmentListener")
        }
    }

    interface FragmentBButtonListener {
        fun onButtonClickListener(someNumber: Int)
    }
}