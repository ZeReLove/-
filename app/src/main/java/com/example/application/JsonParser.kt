package com.example.application

import org.json.JSONArray
import org.json.JSONObject

class JsonParser  {

    companion object{

        fun parseItems(items : MutableList<DataItems>, jsonBody : JSONArray) : MutableList<DataItems> {

            for (i in 0 until jsonBody.length()){
                val infoJsonObject : JSONObject = jsonBody.getJSONObject(i)

                lateinit var name : String
                var isEnable : Boolean
                var text : String
                lateinit var item : DataItems

                if (infoJsonObject.has("name"))
                    name = infoJsonObject.getString("name")
                if (infoJsonObject.has("isEnabled")) {
                    isEnable = infoJsonObject.getBoolean("isEnabled")
                    item = DataItems(name, isEnable, null)
                }
                if (infoJsonObject.has("text")){
                    text = infoJsonObject.getString("text")
                    item = DataItems(name, null, text)
                }

                items.add(item)
            }
            return items
        }
    }
}

