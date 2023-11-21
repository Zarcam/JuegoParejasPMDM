package com.example.juegoparejaspmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.core.view.children
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.juegoparejaspmdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mapCategorias: HashMap<String, Categoria> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.barraHerramientas))

        binding.restartButton.setOnClickListener{
            startGame("Comida")
            findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawer(GravityCompat.START)
        }

        initCategorias()
        startGame("Comida")
    }

    private fun startGame(nombreCategoria: String){
        var adaptador = mapCategorias.get(nombreCategoria)?.let { CartaAdapter(it) }
        val layoutManager = GridLayoutManager(this, 4)
        binding.gridJuego.layoutManager = layoutManager
        binding.gridJuego.adapter = adaptador
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.closeButton -> {
                Log.d("Boton", "Cerrar")
                finish()
                true
            }
            R.id.openNavigationButton -> {
                findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initCategorias(){
        val categoriaComida = Categoria("Comida")
        categoriaComida.addAll(arrayOf(
            R.drawable.banana_blackbarry_blackberries_svgrepo_com,
            R.drawable.cerejas_cherries_cherry_svgrepo_com,
            R.drawable.cupcake_svgrepo_com,
            R.drawable.donut_svgrepo_com,
            R.drawable.fruit_melancia_watermelon_svgrepo_com,
            R.drawable.hamburger_svgrepo_com,
            R.drawable.orange_svgrepo_com,
            R.drawable.pizza_svgrepo_com))

        val categoriaAnimales = Categoria("Animales")
        categoriaAnimales.addAll(arrayOf(
            R.drawable.bear_svgrepo_com,
            R.drawable.bird_svgrepo_com,
            R.drawable.camel_svgrepo_com,
            R.drawable.cat_with_wry_smile_svgrepo_com,
            R.drawable.crocodile_svgrepo_com,
            R.drawable.dog_face_svgrepo_com,
            R.drawable.dolphin_svgrepo_com,
            R.drawable.mouse_svgrepo_com))

        mapCategorias.put(categoriaComida.getNombre(), categoriaComida)
        mapCategorias.put(categoriaAnimales.getNombre(), categoriaAnimales)
    }
}