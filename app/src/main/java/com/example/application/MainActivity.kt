package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.*

class MainActivity : AppCompatActivity() {

    lateinit var model : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(this).get(MyViewModel()::class.java)
        model.load(resources)

        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val actionViewItem: MenuItem = menu.findItem(R.id.item_button)
        val v: View = MenuItemCompat.getActionView(actionViewItem)
        val button = v.findViewById<Button>(R.id.button)

        val items: MutableList<DataItems> = model.items

        button.setOnClickListener(View.OnClickListener {
            println(items)
            reverseParseItems(items)

        })
        return super.onPrepareOptionsMenu(menu)
    }







    //TODO: move to CustomRecyclerView

}

