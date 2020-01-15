package com.example.application

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    val model: MyViewModel
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    var items: MutableList<DataItems> = model.items

    private val FIRST_TYPE : Int = 1
    private val SECOND_TYPE : Int = 2

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_view)

        val checkBox: CheckBox? = itemView.findViewById(R.id.checkbox)

        val editText: EditText? = itemView.findViewById(R.id.edit_text_view)

    }

    override fun getItemViewType(position: Int): Int {
        if (items[position].isEnable != null)
            return FIRST_TYPE
        else
            return SECOND_TYPE
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
            holder.textView.text = items[position].name
            holder.checkBox?.isChecked = items[position].isEnable!!
        }
        else{
            holder.textView.text = items[position].name
            holder.editText?.setText(items[position].text)
        }

        holder.checkBox?.setOnCheckedChangeListener { _, isChecked: Boolean ->
            model.changeIsEnable(position, isChecked)
            println(items)
        }
        holder.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                model.changeText(position, s)
                println(items)
            }
        })
    }

    override fun getItemCount() = items.size

}