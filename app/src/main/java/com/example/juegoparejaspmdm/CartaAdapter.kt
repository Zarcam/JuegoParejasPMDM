package com.example.juegoparejaspmdm
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CartaAdapter(private val categoria: Categoria) : RecyclerView.Adapter<CartaAdapter.CardViewHolder>() {
    class CardViewHolder(view: View) : ViewHolder(view){
        val image: ImageView
        init {
            image = view.findViewById(R.id.cardImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)

        return CardViewHolder(view)

    }

    override fun getItemCount(): Int {
        return categoria.getCartas().size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.image.setImageResource(this.categoria.getCartas()[position].getIdImagen())
    }
}