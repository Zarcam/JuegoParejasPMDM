package com.example.juegoparejaspmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SpinnerAdapter
import androidx.core.view.GravityCompat
import androidx.core.view.children
import androidx.core.view.size
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.juegoparejaspmdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CardListener {
    private val COLUMN_SPAN = 4
    private val INITIAL_ATTEMPTS = 12

    private lateinit var binding: ActivityMainBinding
    private val mapCategorias: HashMap<String, Categoria> = HashMap()

    private lateinit var baraja: ArrayList<Carta>
    private var cartaMostrada1: Carta? = null
    private var cartaMostrada2: Carta? = null

    private var attempts = INITIAL_ATTEMPTS

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.barraHerramientas))

        initCategorias()

        binding.selectCategory.adapter = ArrayAdapter<Any?>(this,
            android.R.layout.simple_spinner_item,
            mapCategorias.keys.toTypedArray())

        binding.restartButton.setOnClickListener{
            startGame(binding.selectCategory.selectedItem.toString())
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }

        startGame(binding.selectCategory.selectedItem.toString())
    }

    override fun onClick(position: Int, carta: Carta) {
        if(carta.getEstado() == Carta.Estados.OCULTO && attempts > 0) {
            if (cartaMostrada1 == null) {
                cartaMostrada1 = carta
            } else if (cartaMostrada2 == null) {
                cartaMostrada2 = carta
            } else {
                if (cartaMostrada1?.getImagen() == cartaMostrada2?.getImagen()) {
                    cartaMostrada1?.setEstado(Carta.Estados.RESUELTO)
                    cartaMostrada2?.setEstado(Carta.Estados.RESUELTO)
                } else {
                    cartaMostrada1?.setEstado(Carta.Estados.OCULTO)
                    cartaMostrada2?.setEstado(Carta.Estados.OCULTO)
                    attempts--
                }
                binding.gridJuego.adapter?.notifyItemChanged(this.baraja.indexOf(cartaMostrada1))
                binding.gridJuego.adapter?.notifyItemChanged(this.baraja.indexOf(cartaMostrada2))
                cartaMostrada1 = carta
                cartaMostrada2 = null
            }
            carta.setEstado(Carta.Estados.MOSTRADO)
            binding.gridJuego.adapter?.notifyItemChanged(position)
        }

        if(checkWin()){
            binding.attemptsText.text = "HAS GANADO"
        }else if(attempts < 1){
            binding.attemptsText.text = "Has perdido :("
        }else{
            binding.attemptsText.text = "Intentos Restantes: $attempts"
        }
    }

    private fun checkWin(): Boolean{
        for(c in baraja){
            if(c.getEstado() == Carta.Estados.OCULTO){
                return false
            }
        }
        return true
    }

    private fun startGame(nombreCategoria: String){
        baraja = ArrayList()
        mapCategorias.get(nombreCategoria)?.getCartas()?.forEach{
            baraja.add(Carta(it))
            baraja.add(Carta(it))
        }
        baraja.shuffle()

        val adaptador = CartaAdapter(baraja)
        val layoutManager = GridLayoutManager(this, COLUMN_SPAN)
        binding.gridJuego.layoutManager = layoutManager
        binding.gridJuego.adapter = adaptador
        adaptador.setCardListener(this)

        attempts = INITIAL_ATTEMPTS
        binding.attemptsText.text = "Intentos Restantes: $attempts"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.closeButton -> {
                finish()
                true
            }
            R.id.openNavigationButton -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
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