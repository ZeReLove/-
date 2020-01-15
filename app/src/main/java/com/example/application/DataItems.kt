package com.example.application

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

@Serializable
data class DataItems(
    var name : String,
    var isEnable : Boolean?,
    var text : String?
)

fun reverseParseItems(items : MutableList<DataItems>){
    println(Json.stringify(DataItems.serializer().list, items))
}
