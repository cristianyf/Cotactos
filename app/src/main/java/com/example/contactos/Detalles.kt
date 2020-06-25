package com.example.contactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detalles.*
import kotlinx.android.synthetic.main.activity_main.tbToolbar

class Detalles : AppCompatActivity() {

    var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        setSupportActionBar(tbToolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        index = intent.getStringExtra("ID").toInt()

       mapearDatos()
    }

    fun mapearDatos(){
        val contacto = MainActivity.obtenerContacto(index)

        tvNombreNuevo.text = contacto.nombre + " " + contacto.apellidos
        tvEmpresaNuevo.text = contacto.empresa
        tvEdadDeta.text = contacto.edad.toString() + " años"
        tvPesoNuevo.text = contacto.peso.toString() + " kg"
        tvTelefonoNuevo.text = contacto.telefono
        tvEmailNuevo.text = contacto.email
        tvDireccionNuevo.text = contacto.direccion
        ivFotoNuevo.setImageResource(contacto.foto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.iEditar -> {
                val intent = Intent(this, Nuevo::class.java)
                intent.putExtra("ID", index.toString())
                startActivity(intent)
                return true
            }

            R.id.iEliminar -> {
                MainActivity.eliminarContacto(index)
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapearDatos()
    }
}
