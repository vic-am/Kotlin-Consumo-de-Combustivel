package com.victoramaral.gastodeviagem

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val viewId = v.id
        if (viewId == R.id.buttonCalculate) calculateButtonAction()
    }

    private fun calculateButtonAction() {
        if (validateIsEmpty()) {
            calculate()
        }
    }

    private fun calculate() {
        try {
            val distance = editTextDistance.text.toString().toFloat()
            val price = editTextPrice.text.toString().toFloat()
            val autonomy = editTextAutonomy.text.toString().toFloat()

            val result = (distance * price) / autonomy

            textViewResult.text = "R$ ${"%.2f".format(result)}"
        } catch (e: Exception) {
            println("Não foi possível realizar o cálculo. ERRO ${e.message}")
        }
    }

    private fun validateIsEmpty(): Boolean {
        textInputLayout.error = null
        textInputLayout2.error = null
        textInputLayout3.error = null
        return when {
            editTextDistance.text.toString() == "" -> {
                textInputLayout.error = "Por favor, insira a distância a ser percorrida"
                false
            }
            editTextPrice.text.toString() == "" -> {
                textInputLayout2.error = "Por favor, insira o preço do combustível"
                false
            }
            editTextAutonomy.text.toString() == "" -> {
                textInputLayout3.error = "Por favor, insira a autonomia prevista do veículo"
                false
            }
            else -> {
                true
            }
        }
    }
}