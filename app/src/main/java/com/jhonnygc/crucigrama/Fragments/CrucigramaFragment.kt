package com.jhonnygc.crucigrama.Fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.jhonnygc.crucigrama.R
import com.jhonnygc.crucigrama.databinding.FragmentCrucigramaBinding

class CrucigramaFragment : Fragment() {

    private var _binding : FragmentCrucigramaBinding? = null
    private val binding get() = _binding!!
    data class Palabra(val palabra: String, val pista: String)
    private val palabraEjemplo = Palabra("ANDROID", "Un sistema operativo móvil desarrollado por Google.")
    private var letrasCorrectas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val palabra = palabraEjemplo.palabra.toCharArray()
        val gridLayout = binding.gridLayout

        for (i in 0 until gridLayout.rowCount) {
            for (j in 0 until gridLayout.columnCount) {
                val button = Button(requireContext())
                button.text = palabra[i * gridLayout.columnCount + j].toString()
                button.setBackgroundResource(R.drawable.grid_button_background) // Personaliza el fondo
                button.setOnClickListener {
                    onCasillaClic(button, palabra[i * gridLayout.columnCount + j])
                }
                gridLayout.addView(button)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCrucigramaBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onCasillaClic(button: Button, letra: Char) {
        if (letra == palabraEjemplo.palabra[letrasCorrectas]) {
            button.setBackgroundColor(Color.GREEN)
            letrasCorrectas++

            if (letrasCorrectas == palabraEjemplo.palabra.length) {
                mostrarMensaje("¡Has completado el crucigrama!")
            }
        } else {
            mostrarMensaje("Letra incorrecta")
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
    }

}