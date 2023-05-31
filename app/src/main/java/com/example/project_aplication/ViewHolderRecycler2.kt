package com.example.project_aplication

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_aplication.databinding.ItemListaTareasBinding
import com.example.project_aplication.databinding.ItemRecyclerviewBinding
import kotlin.random.Random

class ViewHolderRecycler2 (view: View): RecyclerView.ViewHolder(view) {
    val binding2 = ItemListaTareasBinding.bind(view)
    //Recibiendo el ID del item_superhero para llenar en el recyclerView
    var textoTemplate = view.findViewById<RadioButton>(R.id.idRBRecycler)

    fun render(item2: ItemsViewModel2,
               onClickDelete: (Int) -> Unit
               ) {
        binding2.idRBRecycler.text = item2.textoTemplate

        binding2.idRBRecycler.setOnClickListener{
            // Aplica el tachado al texto
            binding2.idRBRecycler.paintFlags = binding2.idRBRecycler.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            onClickDelete(adapterPosition)

        }
        main()
    }
    fun generarColorAleatorio(): Int {
        val random = Random(System.currentTimeMillis())

        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)

        return Color.rgb(red, green, blue)
    }
    fun main(){
        // Obtén el drawable del RadioButton
        var drawable: Drawable = binding2.idRBRecycler.background
        val colorAleatorio = generarColorAleatorio()
        drawable.setColorFilter(colorAleatorio, PorterDuff.Mode.SRC_ATOP)
        // Establece el drawable modificado en el RadioButton
        binding2.idRBRecycler.background = drawable
    }

}