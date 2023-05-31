package com.example.project_aplication

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity2 : AppCompatActivity() {
    lateinit var imgB_close : ImageButton
    lateinit var et_Template: EditText
    private lateinit var FloatingActionButton2: FloatingActionButton

    //RecyclerView2
    private var ItemMutableList2:MutableList<ItemsViewModel2> = ProviderRecycler2.ProviderList2.toMutableList()
    private lateinit var adapter2: AdapterRecycler2
    val manager2 = LinearLayoutManager(this)

    private lateinit var listaElementos: MutableList<String>
    
    //fecha
    private lateinit var edtFecha: EditText
    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        imgB_close = findViewById<ImageButton>(R.id.iB_close) as ImageButton

        imgB_close.setOnClickListener {
            finish()
        }
        listaElementos = mutableListOf()
        et_Template = findViewById<EditText>(R.id.et1) as EditText
        FloatingActionButton2 = findViewById(R.id.fab2)
        FloatingActionButton2.setOnClickListener {
            val nuevoElemento = et_Template.text.toString().trim()
            if (nuevoElemento.isNotEmpty()) {
                var nuevoItem = ItemsViewModel2(
                    nuevoElemento
                )
                listaElementos.add(nuevoElemento);
                //adapter2.notifyItemInserted(ItemMutableList2.size - 1);

                et_Template.text.clear()

                val intent = Intent(this, MainActivity::class.java)
                intent.putStringArrayListExtra("elementos", ArrayList(listaElementos))
                startActivity(intent)
            }
            //finish()
        }
        //Fecha
        edtFecha = findViewById(R.id.etFecha)

        edtFecha.setOnClickListener {
            showDatePickerDialog()
        }
    }
    private fun showDatePickerDialog() {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val seleccionarFecha = dateFormat.format(calendar.time)

            edtFecha.setText(seleccionarFecha)
        }

        val datePickerDialog = DatePickerDialog(
            this,
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }
}