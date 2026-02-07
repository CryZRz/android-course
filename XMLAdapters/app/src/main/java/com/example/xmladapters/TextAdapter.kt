package com.example.xmladapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmladapters.databinding.ItemListBasicBinding

class TextAdapter(private val items: List<String>): RecyclerView.Adapter<TextAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TextAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_basic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextAdapter.ViewHolder, position: Int) {
        holder.binding.mainText.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemListBasicBinding.bind(view)
    }
}

