package com.example.application

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import java.io.BufferedReader

class MyViewModel : ViewModel() {

    val items = mutableListOf<DataItems>()

    fun load(resources: Resources){
        if(items.isEmpty()) {
            items.addAll(parse2(resources))
        }
    }

    fun changeIsEnable(position : Int, isChecked : Boolean){
        items[position].isEnable = isChecked
    }

    fun changeText(position: Int, s: CharSequence?){
        items[position].text = s.toString()
    }

    private fun parse(resources: Resources) : JSONArray {
        val f  = resources.openRawResource(R.raw.data)
        val content = f.bufferedReader().use(BufferedReader::readText)
        val obj = JSONArray(content)
        return obj
    }

    private fun parse2(resources: Resources) : MutableList<DataItems> {
        val obj = parse(resources)
        val items = mutableListOf<DataItems>()
        val item = JsonParser.parseItems(items, obj)
        return item
    }
}