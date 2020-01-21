package com.example.application

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DataListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val model: MyViewModel by lazy {
        val model = ViewModelProvider(this).get(MyViewModel()::class.java)
        model.load(resources)
        model
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_recycler, container, false)
        viewManager = LinearLayoutManager(activity)
        viewAdapter = CustomAdapter(model)

        setHasOptionsMenu(true)
        super.onCreateView(inflater, container, savedInstanceState)

        recyclerView = root.findViewById(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return root
    }

    @kotlinx.serialization.ImplicitReflectionSerializer
    @kotlinx.serialization.UnstableDefault
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
            val actionViewItem: MenuItem = menu.findItem(R.id.item_button)
            val v: View = MenuItemCompat.getActionView(actionViewItem)
            val button = v.findViewById<Button>(R.id.button)

            button.setOnClickListener {
                reverseParseItems(model.items)
            }
    }

}

