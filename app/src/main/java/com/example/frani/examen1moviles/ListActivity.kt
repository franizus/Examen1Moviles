package com.example.frani.examen1moviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    lateinit var adaptador: AutorAdapter
    lateinit var autores: ArrayList<Autor>
    lateinit var dbHandler: DBAutorHandlerAplicacion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        dbHandler = DBAutorHandlerAplicacion(this)
        autores = dbHandler.getAutorsList()

        val layoutManager = LinearLayoutManager(this)
        adaptador = AutorAdapter(autores)
        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var autor = autores.get(position)

        when (item.getItemId()) {
            R.id.item_menu_editar -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("autor", autor)
                startActivity(intent)
                return true
            }
            R.id.item_menu_borrar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(R.string.confirmation)
                        .setPositiveButton(R.string.yes, { dialog, which ->
                            dbHandler.deleteAutor(autor.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton(R.string.no, null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
