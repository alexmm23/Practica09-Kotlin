// GuardabosqueAdapter.kt
package com.example.practica09almacenamiento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Guardabosque(val nombre: String, val edad: Int, val experiencia: String, val parque: String)

class GuardabosqueAdapter(private val guardabosques: List<Guardabosque>) : RecyclerView.Adapter<GuardabosqueAdapter.GuardabosqueViewHolder>() {

    class GuardabosqueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val edadTextView: TextView = itemView.findViewById(R.id.edadTextView)
        val experienciaTextView: TextView = itemView.findViewById(R.id.experienciaTextView)
        val parqueTextView: TextView = itemView.findViewById(R.id.parqueTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuardabosqueViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_guardabosque, parent, false)
        return GuardabosqueViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GuardabosqueViewHolder, position: Int) {
        val guardabosque = guardabosques[position]
        holder.nombreTextView.text = guardabosque.nombre
        holder.edadTextView.text = guardabosque.edad.toString()
        holder.experienciaTextView.text = guardabosque.experiencia
        holder.parqueTextView.text = guardabosque.parque
    }

    override fun getItemCount() = guardabosques.size
}