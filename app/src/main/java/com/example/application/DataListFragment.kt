package com.example.application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DataListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        val root = inflater.inflate(R.layout.fragment_recycler, container, false)
        viewManager = LinearLayoutManager(activity)
        val act = activity as MainActivity
        viewAdapter = CustomAdapter(act.model)

        recyclerView = root.findViewById(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return root
    }
}