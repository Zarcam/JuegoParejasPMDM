package com.example.juegoparejaspmdm

class Categoria(private val nombre: String) {
    private val cartas: ArrayList<Carta> = ArrayList()

    fun add(carta: Carta){
        this.cartas.add(carta)
    }

    fun addAll(nCartas: Array<Carta>){
        this.cartas.addAll(nCartas)
    }

    fun getCartas(): ArrayList<Carta>{
        return this.cartas
    }

    fun getNombre(): String{
        return this.nombre
    }
}