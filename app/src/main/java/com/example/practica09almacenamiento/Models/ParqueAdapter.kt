package com.example.practica09almacenamiento.Models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practica09almacenamiento.R

data class Parque(
    val nombre: String,
    val ubicacion: String,
    val tamano: Double,
    val flora: String,
    val fauna: String
) {
    override fun toString(): String {
        return "$nombre, $ubicacion, $tamano, $flora, $fauna"
    }
}

class ParqueAdapter(private val parques: List<Parque>) : RecyclerView.Adapter<ParqueAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val ubicacionTextView: TextView = itemView.findViewById(R.id.ubicacionTextView)
        val tamanoTextView: TextView = itemView.findViewById(R.id.tamanoTextView)
        val floraTextView: TextView = itemView.findViewById(R.id.floraTextView)
        val faunaTextView: TextView = itemView.findViewById(R.id.faunaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parque, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parque = parques[position]
        holder.nombreTextView.text = parque.nombre
        holder.ubicacionTextView.text = parque.ubicacion
        holder.tamanoTextView.text = parque.tamano.toString()
        holder.floraTextView.text = parque.flora
        holder.faunaTextView.text = parque.fauna
    }

    override fun getItemCount(): Int = parques.size
}