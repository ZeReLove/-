package com.example.application

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject


class CustomAdapter(
    private val items: MutableList<DataItems>,
    val jsonArray: JSONArray,
    val activity: MainActivity
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    private val FIRST_TYPE : Int = 1
    private val SECOND_TYPE : Int = 2

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null

        init {
            textView = itemView.findViewById(R.id.text_view)
        }

        var checkBox: CheckBox? = null

        init {
            checkBox = itemView.findViewById(R.id.checkbox)
        }

        var editText: EditText? = null

        init {
            editText = itemView.findViewById(R.id.edit_text_view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            3 -> SECOND_TYPE
            else -> FIRST_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView : View
        if (viewType == FIRST_TYPE)
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rec_view, parent, false)
        else
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_text_view, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (getItemViewType(position) == FIRST_TYPE){
            holder.textView?.text = items[position].name
            holder.checkBox?.isChecked = items[position].isEnable
        }
        else{
            holder.textView?.text = items[position].name
            holder.editText?.setText(items[position].text)
        }

        holder.checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                items[position].isEnable = true
                JsonParser.changeJson(false, jsonArray, position)
            } else {
                items[position].isEnable = false
                JsonParser.changeJson(true, jsonArray, position)
            }
            //Log.d("MyLog", "Hey!")
            //println(jsonArray)
        }
        holder.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                items[position].text = s.toString()
                activity.changeJson(s.toString(), position)
                //println(jsonArray)
            }

        })

    }

    override fun getItemCount() = items.size

}