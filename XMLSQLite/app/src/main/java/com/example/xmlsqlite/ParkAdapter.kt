package com.example.xmlsqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlsqlite.databinding.ItemParkBinding

class ParkAdapter(private val parks: MutableList<Park>, private val listener: OnClickListener): RecyclerView.Adapter<ParkAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_park, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val park = parks[position]
        holder.setListener(park)
        with(holder.binding){
            tvName.text = park.name
            cbFavorite.isChecked = park.isFav
        }
    }

    override fun getItemCount(): Int {
        return parks.size
    }

    fun add(park: Park){
        if (!parks.contains(park)){
            parks.add(park)
            notifyItemInserted(parks.size-1)
        }
    }

    fun remove(park: Park) {
        val index = parks.indexOf(park)
        parks.removeAt(index)
        notifyItemRemoved(index)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemParkBinding.bind(view)

        fun setListener(park: Park){
            binding.cbFavorite.setOnClickListener {
                park.isFav = (it as CheckBox).isChecked
                listener.onClick(park)
            }

            binding.root.setOnLongClickListener {
                listener.onLongClick(park)
                true
            }
        }
    }
}