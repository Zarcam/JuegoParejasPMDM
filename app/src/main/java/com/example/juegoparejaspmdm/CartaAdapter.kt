package com.example.juegoparejaspmdm
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CartaAdapter(private val baraja: ArrayList<Carta>) : RecyclerView.Adapter<CartaAdapter.CardViewHolder>() {
    private lateinit var cardListener: CardListener

    class CardViewHolder(view: View) : ViewHolder(view){
        val image: ImageView

        init {
            image = view.findViewById(R.id.cardImageView)
        }
    }

    public fun setCardListener(cardListener: CardListener){
        this.cardListener=cardListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)

        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return baraja.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val carta: Carta = this.baraja[position]
        holder.image.setImageResource(carta.getImagen())

        holder.image.setOnClickListener{
            this.cardListener.onClick(position,carta)
            /*if(carta.getEstado() == Carta.Estados.OCULTO) {
                if(cartaMostrada1 == null){
                    cartaMostrada1 = carta
                }else if(cartaMostrada2 == null){
                    cartaMostrada2 = carta
                }else{
                    if(cartaMostrada1?.getImagen() == cartaMostrada2?.getImagen()){
                        cartaMostrada1?.setEstado(Carta.Estados.RESUELTO)
                        cartaMostrada2?.setEstado(Carta.Estados.RESUELTO)
                    }else{
                        cartaMostrada1?.setEstado(Carta.Estados.OCULTO)
                        cartaMostrada2?.setEstado(Carta.Estados.OCULTO)
                    }
                    this.notifyItemChanged(this.baraja.indexOf(cartaMostrada1))
                    this.notifyItemChanged(this.baraja.indexOf(cartaMostrada2))
                    cartaMostrada1 = carta
                    cartaMostrada2 = null
                }


                carta.setEstado(Carta.Estados.MOSTRADO)
                this.notifyItemChanged(position)
            }*/
        }
    }
}