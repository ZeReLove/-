package com.example.application

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.json.JSONArray

sealed class DataItems
@Serializable
data class DataItemBool(var name: String, var isEnabled: Boolean) : DataItems()
@Serializable
data class DataItemText(var name: String, var text: String) : DataItems()
@Serializable
data class DataItemArray(var name: String, var elements: List<Int>, var selected: Int) : DataItems()


@kotlinx.serialization.ImplicitReflectionSerializer
@kotlinx.serialization.UnstableDefault
fun reverseParseItems(items : MutableList<DataItems>){
    val jsonBody = JSONArray()
    lateinit var jsonObject: JsonElement
    for (i in 0 until items.size){
        val item : DataItems = items[i]

        jsonObject = when (item){
            is DataItemBool -> Json.nonstrict.toJson(DataItemBool.serializer(), item)
            is DataItemText -> Json.nonstrict.toJson(DataItemText.serializer(), item)
            is DataItemArray -> Json.nonstrict.toJson(DataItemArray.serializer(), item)
        }
    jsonBody.put(jsonObject)
    }
}
