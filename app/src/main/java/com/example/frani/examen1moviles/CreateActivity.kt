package com.example.frani.examen1moviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    lateinit var dbHandler: DBAutorHandlerAplicacion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        dbHandler = DBAutorHandlerAplicacion(this)

        btnCrearAutor.setOnClickListener{
            v: View? ->  crearAutor()
        }
    }

    fun crearAutor() {
        var nombre = txtNombreAutor.text.toString()
        var apellido = txtApellidoAutor.text.toString()
        var fecha = txtFechaAutor.text.toString()
        var numeroLibros = txtNumeroLibrosAutor.text.toString().toInt()
        var ecutoriano = if (switchEcAutor.isChecked) 1 else 0
        var autor = Autor(0, nombre, apellido, fecha, numeroLibros, ecutoriano)

        dbHandler.insertarAutor(autor)

        irAListView()
    }

    fun irAListView() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}
