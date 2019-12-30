package com.example.application

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.json.JSONArray
import org.json.JSONObject
import java.util.jar.Attributes

class JsonParser  {

    companion object{

        fun parseItems(items : MutableList<DataItems>, jsonBody : JSONArray) : MutableList<DataItems> {

            for (i in 0 until jsonBody.length()){
                val infoJsonObject : JSONObject = jsonBody.getJSONObject(i)

                var item = DataItems()
                if (infoJsonObject.has("name"))
                    item.name = infoJsonObject.getString("name")
                if (infoJsonObject.has("isEnabled"))
                    item.isEnable = infoJsonObject.getBoolean("isEnabled")
                if (infoJsonObject.has("text"))
                    item.text = infoJsonObject.getString("text")
                items.add(item)
            }
            return items
        }
        fun changeJson(bool : Boolean, jsonBody : JSONArray, position : Int){
            val infoJsonObject : JSONObject = jsonBody.getJSONObject(position)
                if (bool == true)
                    infoJsonObject.put("isEnabled", "false")
                else
                    infoJsonObject.put("isEnabled", "true")
        }
        fun changeText(str : String, jsonBody: JSONArray, position: Int){
            val infoJsonObject : JSONObject = jsonBody.getJSONObject(position)
            infoJsonObject.put("text", str)
        }
    }
}

