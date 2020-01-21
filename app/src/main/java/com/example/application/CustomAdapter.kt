package com.example.application

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

private const val TEXT_ID = 0
private const val BOOL_ID = 1
private const val ARRAY_ID = 2

class CustomAdapter(
    private val model: MyViewModel
) : RecyclerView.Adapter<CustomAdapter.MyViewHolders>() {

    private val mapOfBuilding = mapOf<Int, (ViewGroup) -> MyViewHolders>(
        TEXT_ID to { parent ->
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_text_view, parent, false)
            TextViewHolder(itemView, model)
        },
        BOOL_ID to {parent ->
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_rec_view, parent, false)
            BoolViewHolder(itemView, model)
        },
        ARRAY_ID to {parent ->
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_spinner_view, parent, false)
            ArrayViewHolder(itemView, model)
        }
    )

    private var items: MutableList<DataItems> = model.items

    abstract class MyViewHolders(itemView: View, model: MyViewModel) : RecyclerView.ViewHolder(itemView) {

        abstract fun bindData(data: DataItems, holder: MyViewHolders)
    }


    class TextViewHolder(itemView: View, private val model: MyViewModel) : MyViewHolders(itemView, model) {
        private val textView: TextView = itemView.findViewById(R.id.text_view)

        private val editText: EditText? = itemView.findViewById(R.id.edit_text_view)

        override fun bindData(data: DataItems, holder: MyViewHolders) {
            val textItem = data as DataItemText
            val textViewHolder: TextViewHolder = holder as TextViewHolder

            textViewHolder.textView.text = textItem.name
            textViewHolder.editText?.setText(textItem.text)

            textViewHolder.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    model.changeText(s, textItem)
                }
            })
        }
    }

    class BoolViewHolder(itemView: View, private val model: MyViewModel) : MyViewHolders(itemView, model) {
        private val textView: TextView = itemView.findViewById(R.id.text_view)

        private val checkBox: CheckBox? = itemView.findViewById(R.id.checkbox)

        override fun bindData(data: DataItems, holder: MyViewHolders) {
            val boolItem = data as DataItemBool
            val boolViewHolder: BoolViewHolder = holder as BoolViewHolder

            boolViewHolder.textView.text = boolItem.name
            boolViewHolder.checkBox?.isChecked = boolItem.isEnabled

            boolViewHolder.checkBox?.setOnCheckedChangeListener { _, isChecked: Boolean ->
                model.changeIsEnable(isChecked, boolItem)
            }
        }
    }

    class ArrayViewHolder(itemView: View, private val model: MyViewModel) : MyViewHolders(itemView, model) {
        private val textView: TextView = itemView.findViewById(R.id.text_view)

        private val elements: Spinner = itemView.findViewById(R.id.spinner)

        private val selected: TextView = itemView.findViewById(R.id.selected)

        override fun bindData(data: DataItems, holder: MyViewHolders) {
            val arrayItem = data as DataItemArray
            val arrayViewHolder: ArrayViewHolder = holder as ArrayViewHolder

            elements.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    model.changeSelected(position, arrayItem)
                    arrayViewHolder.selected.text = arrayItem.selected.toString()
                }
            }

            ArrayAdapter<Int>(
                itemView.context,
                android.R.layout.simple_spinner_item,
                arrayItem.elements
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                elements.adapter = adapter
            }

            arrayViewHolder.elements.setSelection(arrayItem.selected)
            arrayViewHolder.textView.text = arrayItem.name
            arrayViewHolder.selected.text = arrayItem.selected.toString()
        }
    }

    override fun getItemViewType(position: Int): Int
    {
        val dataItem = items[position]

        val ret = when (dataItem) {
            is DataItemText -> TEXT_ID
            is DataItemBool -> BOOL_ID
            is DataItemArray -> ARRAY_ID
        }
        return ret
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolders {
       return checkNotNull(mapOfBuilding[viewType])(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolders, position: Int) {
        val dataItem = items[position]
        holder.bindData(dataItem, holder)
    }

    override fun getItemCount() = items.size
}

