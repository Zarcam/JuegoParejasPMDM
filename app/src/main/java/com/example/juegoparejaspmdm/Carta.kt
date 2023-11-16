package com.example.juegoparejaspmdm

class Carta (private val id: Int){
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

    fun getId(): Int{
        return this.id
    }
}