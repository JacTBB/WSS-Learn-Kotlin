package com.example.myfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.DialogFragment.STYLE_NORMAL

class DialogShowFragment : DialogFragment() {
    var displayMsg: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_dialog_show, container, false)
        val displayMsgTV = v.findViewById<TextView>(R.id.displayMsgTV)
        displayMsgTV.text = displayMsg?: "No Msg to display"
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setTitle("Number Added")
    }

    companion object {
        fun newInstance(msg: String): DialogShowFragment {
            val fragment = DialogShowFragment()
            fragment.setStyle(STYLE_NORMAL, R.style.DialogShowFragment)
            fragment.displayMsg = msg
            return fragment
        }
    }
}