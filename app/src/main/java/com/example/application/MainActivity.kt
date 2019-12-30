package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    private var recyclerFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        recyclerFragment = CustomRecyclerView()
        fragmentTransaction.add(R.id.inner_frame_layout, recyclerFragment!!)
        fragmentTransaction.commit()
    }

    fun parse() : JSONArray{
        var f  = resources.openRawResource(R.raw.data)
        val content = f.bufferedReader().use(BufferedReader::readText)
        val obj = JSONArray(content)
        return obj
    }

    fun parse2() : MutableList<DataItems> {
        val obj = parse()
        val items = mutableListOf<DataItems>()
        val item = JsonParser.parseItems(items, obj)
        return item
    }

    fun changeJson(str : String, pos : Int){
        val arrJson : JSONArray = parse()
        val objJson : JSONObject = arrJson.getJSONObject(pos)
        objJson.put("text", str)
        //println(objJson)
    }
}

