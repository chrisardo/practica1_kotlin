package com.example.project_aplication

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.RadioButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_aplication.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.appcompat.widget.SearchView.OnQueryTextListener

class MainActivity : AppCompatActivity(), OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var radioButton2: RadioButton
    private lateinit var FloatingActionButton: FloatingActionButton

    //lateinit var toolbar: Toolbar
    lateinit var searchView: SearchView

    //RecyclerView1
    private var ItemMutableList: MutableList<ItemsViewModel> = ProviderRecycler.ProviderList.toMutableList()
    private lateinit var adapter: AdapterRecycler
    val manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    //RecyclerView2
    private var ItemMutableList2: MutableList<ItemsViewModel2> = ProviderRecycler2.ProviderList2.toMutableList()
    private lateinit var adapter2: AdapterRecycler2
    val manager2 = LinearLayoutManager(this)

    private lateinit var listaElementos: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        radioButton2 = findViewById(R.id.rBCrear)
        // Obtén el drawable del RadioButton
        val drawable: Drawable = radioButton2.background
        // Cambia el color del círculo del RadioButton
        drawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        // Establece el drawable modificado en el RadioButton
        radioButton2.background = drawable
        FloatingActionButton = findViewById(R.id.fab)
        radioButton2.setOnClickListener {
            // Iniciar la siguiente actividad con otra configuración
            startActivity(Intent(this, MainActivity2::class.java))
        }
        FloatingActionButton.setOnClickListener {
            // Iniciar la siguiente actividad con otra configuración
            startActivity(Intent(this, MainActivity2::class.java))
        }

        listaElementos = intent.getStringArrayListExtra("elementos") ?: mutableListOf()
        if (!listaElementos.isEmpty()) {
            Toast.makeText(this, "recibido", Toast.LENGTH_SHORT).show()
            var nuevoItem = ItemsViewModel2(
                listaElementos.toString()
            )
            ItemMutableList2.add(index = 1, nuevoItem)

        }

        initRecyclerView1()

        adapter2 = AdapterRecycler2(
            ItemMutableList2
        ) { position -> onDeletedItem(position) }
        binding.idRv2.layoutManager = LinearLayoutManager(this)
        binding.idRv2.layoutManager = manager2
        binding.idRv2.adapter = adapter2

        //buscar
        //searchView = findViewById<SearchView>(R.id.ic_search) as SearchView
        binding.icSearch.setOnQueryTextListener(this)
        adapter2.filterList(ItemMutableList2)

    }
    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.action_search)
        //var searchView = searchItem.actionView as SearchView

        /*searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isNullOrEmpty()) {
                    filterList(newText)
                }
                return true
            }
        })*/

        return true
    }*/
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            filterList(newText.toLowerCase())
        }
        return true
    }
    private fun filterList(newText: String) {
        ItemMutableList2.clear()

        if (newText.isEmpty()) {
            ItemMutableList2.addAll(ItemMutableList2)
        } else {
            for (item in ItemMutableList2) {
                if (item.toString().toLowerCase().contains(newText.toLowerCase())) {
                    ItemMutableList2.add(item)
                }
            }
        }

        adapter2.filterList(ItemMutableList2)
    }

    //-----------RecyclerView-------------
    fun initRecyclerView1() {
        adapter = AdapterRecycler(ItemMutableList)
        /*val manager = GridLayoutManager(this, 2)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.idRv1.addItemDecoration(decoration)*/
        //binding.rvListas.layoutManager = LinearLayoutManager(this)
        binding.idRv1.layoutManager = manager
        binding.idRv1.adapter = adapter
    }

    private fun onDeletedItem(position: Int) {
        // Configurar el contador de 5 segundos
        countDownTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                // Actualizar la interfaz de usuario con el tiempo restante
            }

            override fun onFinish() {
                // El contador ha finalizado
                ItemMutableList2.removeAt(position)
                adapter2.notifyItemRemoved(position)
            }
        }
        // Iniciar el contador cuando sea necesario
        startCountdown()
    }

    private fun startCountdown() {
        countDownTimer.start()
    }


    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

}

