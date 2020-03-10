package io.bsu.mmf.helpme.view.map.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.bsu.mmf.helpme.R

typealias BottomSheetViewListener = (BottomSheetFilterViewEvent) -> Unit
class BottomSheetTimeAdapter(
        private val items: ArrayList<Int>
): RecyclerView.Adapter<BottomSheetTimeAdapter.ItemViewHolder>() {

    var listener: BottomSheetViewListener = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_choose_time, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
        holder.containerView.setOnClickListener {
            listener(ClickEvent(items[position]))
        }

    }

    class ItemViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView){


        fun bind(item: Int) {
         containerView.findViewById<TextView>(R.id.tvTimeSelection).text = item.toString()
        }
    }

}