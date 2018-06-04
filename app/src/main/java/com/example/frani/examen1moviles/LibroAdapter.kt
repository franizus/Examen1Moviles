package com.example.frani.examen1moviles

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class LibroAdapter(private val librosList: List<Libro>) : RecyclerView.Adapter<LibroAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombre: TextView
        var fechaPublicacion: TextView
        var editorial: TextView
        var detalles: Button
        lateinit var libro: Libro

        init {
            nombre = view.findViewById(R.id.txt_1) as TextView
            fechaPublicacion = view.findViewById(R.id.txt_2) as TextView
            editorial = view.findViewById(R.id.txt_3) as TextView
            detalles = view.findViewById(R.id.btn_1)as Button
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val libro = librosList[position]
        holder.nombre.setText(libro.nombre)
        holder.fechaPublicacion.setText(libro.fechaPublicacion)
        holder.editorial.setText(libro.nombreEditorial)
        holder.libro = libro
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetailsActivity::class.java)
            intent.putExtra("libro", libro)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return librosList.size
    }

}