package com.example.application

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application.JsonParser.Companion.changeJson
import org.json.JSONArray
import org.json.JSONObject

class CustomRecyclerView : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var myJsonArray: JSONArray

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_recycler, container, false)
        viewManager = LinearLayoutManager(activity)
        val myActivity = activity as MainActivity
        val items = myActivity.parse2()
        val myActivity2 = activity as MainActivity
        myJsonArray = myActivity2.parse()
        viewAdapter = CustomAdapter(items, myJsonArray, myActivity2)

        recyclerView = root.findViewById(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return root
    }
}