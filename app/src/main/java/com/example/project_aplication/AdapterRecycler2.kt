package com.example.project_aplication

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView

class AdapterRecycler2(
    private var ItemList2: List<ItemsViewModel2>,
    private var onClickDelete: (Int) -> Unit
): RecyclerView.Adapter<ViewHolderRecycler2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecycler2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderRecycler2(layoutInflater.inflate(R.layout.item_lista_tareas, parent, false))

    }

    override fun getItemCount(): Int = ItemList2.size

    override fun onBindViewHolder(holder: ViewHolderRecycler2, position: Int) {
        val item2 = ItemList2[position]
       holder.render(item2, onClickDelete)
    }

    fun filterList(itemMutableList2: MutableList<ItemsViewModel2>) {
        this.ItemList2= itemMutableList2
        notifyDataSetChanged()
    }
}