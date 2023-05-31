package com.example.project_aplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterRecycler(
    private var ItemList: List<ItemsViewModel>
): RecyclerView.Adapter<ViewHolderRecycler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecycler {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderRecycler(layoutInflater.inflate(R.layout.item_recyclerview, parent, false))

    }

    override fun getItemCount(): Int = ItemList.size

    override fun onBindViewHolder(holder: ViewHolderRecycler, position: Int) {
        val item = ItemList[position]
        holder.render(item)
    }
}