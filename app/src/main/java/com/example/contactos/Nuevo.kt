package com.example.contactos

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nuevo.*
import kotlinx.android.synthetic.main.template_contacto.*

class Nuevo : AppCompatActivity() {

    var fotoIndex: Int = 0

    val fotos = arrayOf(
        R.drawable.foto_01,
        R.drawable.foto_02,
        R.drawable.foto_03,
        R.drawable.foto_04,
        R.drawable.foto_05,
        R.drawable.foto_06
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)

        setSupportActionBar(tbToolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        ivFotoNuevo.setOnClickListener {
            seleccionarFoto()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nuevo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.iCrearNuevo -> {
                val nombre = findViewById<EditText>(R.id.tvNombreDeta)
                val apellidos = findViewById<EditText>(R.id.tvApellidoDeta)
                val empresa = findViewById<EditText>(R.id.tvEmpresaDeta)
                val edad = findViewById<EditText>(R.id.tvAñosDeta)
                val peso = findViewById<EditText>(R.id.tvPesoDeta)
                val telefono = findViewById<EditText>(R.id.tvTelefonoDeta)
                val email = findViewById<EditText>(R.id.tvEmailDeta)
                val direccion = findViewById<EditText>(R.id.tvDireccionDeta)

                val campos = ArrayList<String>()
                campos.add(nombre.text.toString())
                campos.add(apellidos.text.toString())
                campos.add(empresa.text.toString())
                campos.add(edad.text.toString())
                campos.add(peso.text.toString())
                campos.add(direccion.text.toString())
                campos.add(telefono.text.toString())
                campos.add(email.text.toString())

                var campoVacio = 0
                for (campo in campos) {
                    if (campo.isEmpty())
                        campoVacio++
                }
                if (campoVacio > 0) {
                    Toast.makeText(this, "Rellenar todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    MainActivity.agregarContacto(
                        Contacto(
                            campos.get(0),
                            campos.get(1),
                            campos.get(2),
                            campos.get(3).toInt(),
                            campos.get(4).toFloat(),
                            campos.get(5),
                            campos.get(6),
                            campos.get(7),
                            obtenerFoto(fotoIndex)
                        )
                    )
                    finish()
                    Log.d("NUMERO DE CONTACTOS", MainActivity.contactos?.count().toString())
                }

                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    fun seleccionarFoto() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona la foto de perfil")

        val adaptadorDialogo =
            ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item)
        adaptadorDialogo.add("Foto 01")
        adaptadorDialogo.add("Foto 02")
        adaptadorDialogo.add("Foto 03")
        adaptadorDialogo.add("Foto 04")
        adaptadorDialogo.add("Foto 05")
        adaptadorDialogo.add("Foto 06")

        builder.setAdapter(adaptadorDialogo) { dialog, which ->
            fotoIndex = which
            ivFotoNuevo?.setImageResource(obtenerFoto(fotoIndex))
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    fun obtenerFoto(index: Int): Int {
        return fotos.get(index)
    }
}
