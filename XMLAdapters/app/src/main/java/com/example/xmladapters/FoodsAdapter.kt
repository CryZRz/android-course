package com.example.xmladapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.xmladapters.databinding.ItemListAdvanceBinding

class FoodsAdapter(private var foods: List<Food>, private val listener: onClickListener): RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_advance,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val food = foods[position]
        holder.setListener(food)
        with(holder.binding){
            overlineText.text = food.author
            mainText.text = food.name
            secondaryText.text = food.description

            Glide.with(context)
                .load(food.imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(img)
        }


    }

    override fun getItemCount(): Int {
        return foods.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemListAdvanceBinding.bind(view)

        fun setListener(food: Food){
           binding.root.setOnClickListener {
                listener.onCLick(food)
           }
        }
    }
}