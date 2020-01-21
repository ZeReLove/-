package com.example.application

import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import org.json.JSONArray
import org.json.JSONObject

class JsonParser  {

    companion object{

        fun parseItems(items : MutableList<DataItems>, jsonBody : JSONArray) : MutableList<DataItems> {

            for (i in 0 until jsonBody.length()){
                val infoJsonObject : JSONObject = jsonBody.getJSONObject(i)
                println(infoJsonObject)

                lateinit var item : DataItems

                if (infoJsonObject.has("isEnabled")) {
                    item = Json.nonstrict.parse(DataItemBool.serializer(), infoJsonObject.toString())
                }
                if (infoJsonObject.has("text")){
                    item = Json.nonstrict.parse(DataItemText.serializer(), infoJsonObject.toString())
                }
                if (infoJsonObject.has("elements")){
                    item = Json.nonstrict.parse(DataItemArray.serializer(), infoJsonObject.toString())
                }

                items.add(item)
            }
            return items
        }
    }
}

