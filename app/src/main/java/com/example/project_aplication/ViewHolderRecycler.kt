package com.example.project_aplication

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_aplication.databinding.ItemListaTareasBinding
import com.example.project_aplication.databinding.ItemRecyclerviewBinding
import kotlin.random.Random

class ViewHolderRecycler(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemRecyclerviewBinding.bind(view)

    //Recibiendo el ID del item_superhero para llenar en el recyclerView
    val cantidad_tarea = view.findViewById<TextView>(R.id.tvtareas)
    val tipo_negocio = view.findViewById<TextView>(R.id.tvtipos)
    //val colorAleatorio = generarColorAleatorio()

    fun render(item: ItemsViewModel) {
        binding.tvtareas.text = item.cantidad_tareas
        binding.tvtipos.text = item.tipo_negocios
        main()
    }

    fun generarColorAleatorio(): Int {
        val random = Random(System.currentTimeMillis())

        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)

        return Color.rgb(red, green, blue)
    }

    fun main() {
        val colorAleatorio = generarColorAleatorio()
        binding.barraSeleccionId.setBackgroundColor(colorAleatorio)
        //println("Color aleatorio: #$colorAleatorio")
    }


}