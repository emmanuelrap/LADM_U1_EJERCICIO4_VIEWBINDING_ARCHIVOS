package com.example.ladm_u1_ejercicio4_viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ladm_u1_ejercicio4_viewbinding.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //se llama casi igual que el XML

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //se pone exactamente aqui
        setContentView(binding.root)

        val bGuardar = binding.guardar

        bGuardar.setOnClickListener {
            guardarArchivo()
        }

        binding.leer.setOnClickListener{
            leerArchivo()
        }
    }

    private fun guardarArchivo() {
        try {
            val archivo=OutputStreamWriter(openFileOutput("archivo.txt", MODE_PRIVATE))
            var cadena = binding.nombre.text.toString()+"\n"+
                         binding.domicilio.text.toString()+"\n"+
                         binding.sueldo.text

            archivo.write(cadena)
            archivo.flush()
            archivo.close()

            AlertDialog.Builder(this)
                .setMessage("Guardado")
                .show()
        }catch (e:Exception){
            AlertDialog.Builder(this)
                .setMessage(e.message)
                .show()
        }
    }

    private fun leerArchivo() {
        try {
            val archivo=BufferedReader(InputStreamReader(openFileInput("archivo.txt")))
            var cadena = ""

            archivo.forEachLine {
                cadena+="\n"+archivo.readLine()
            }

            archivo.close()

            AlertDialog.Builder(this).setMessage(cadena).show()
        }catch (e:Exception){
            AlertDialog.Builder(this).setMessage(e.message).show()
        }
    }
}