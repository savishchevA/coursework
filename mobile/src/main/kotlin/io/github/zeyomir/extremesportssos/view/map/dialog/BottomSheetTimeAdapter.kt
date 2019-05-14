package io.github.zeyomir.extremesportssos.view.map.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_choose_time.*
import io.github.zeyomir.extremesportssos.R


class BottomSheetTimeAdapter(
        private val items: ArrayList<Int>
): RecyclerView.Adapter<BottomSheetTimeAdapter.ItemViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_choose_time, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])

    }

    class ItemViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView){


        fun bind(item: Int) {
            //tvTimeSelection
        }
    }

}