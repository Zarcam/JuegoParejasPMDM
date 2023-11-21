package com.example.juegoparejaspmdm

class Categoria(private val nombre: String) {
    private val images: ArrayList<Int> = ArrayList()

    fun add(image: Int){
        this.images.add(image)
    }

    fun addAll(nCartas: Array<Int>){
        this.images.addAll(nCartas)
    }

    fun getCartas(): ArrayList<Int>{
        return this.images
    }

    fun getNombre(): String{
        return this.nombre
    }
}