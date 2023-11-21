package com.example.juegoparejaspmdm

class Carta (private val idImagen: Int){
    private val imagenDorso = R.drawable.poker_svgrepo_com
    private var estado: Estados = Estados.OCULTO


    enum class Estados{
        OCULTO, MOSTRADO, RESUELTO
    }

    fun setEstado(nEstado: Estados){
        this.estado = nEstado
    }

    fun getEstado(): Estados{
        return this.estado
    }

    fun getImagen(): Int{
        return if (estado == Estados.OCULTO) imagenDorso else idImagen
    }
}