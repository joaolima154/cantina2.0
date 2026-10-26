package com.seuprograma.app // Substitua pelo seu pacote real

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.R
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowCompat
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var edtQuantidade: EditText
    private lateinit var txtResultado: TextView
    private lateinit var chkDesconto: CheckBox
    private lateinit var txtDesconto: TextView
    private val precoLanche = 8.0
    private val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val margin = (16 * resources.displayMetrics.density).toInt()

            view.setPadding(
                bars.left + margin,
                bars.top + margin,
                bars.right + margin,
                bars.bottom + margin
            )
            insets
        }

        // Inicialização de todas as Views
        edtQuantidade = findViewById(R.id.edtQuantidade)
        txtResultado = findViewById(R.id.txtResultado)
        chkDesconto = findViewById(R.id.chkDesconto)
        txtDesconto = findViewById(R.id.txtDesconto)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnLimpar = findViewById<Button>(R.id.btnLimpar)

        // Configuração dos Listeners
        btnCalcular.setOnClickListener {
            processarCompra()
        }

        chkDesconto.setOnCheckedChangeListener { _, estaMarcado ->
            txtDesconto.text = if (estaMarcado) {
                "Desconto de 10% ativado"
            } else {
                "Desconto desativado"
            }
            txtResultado.text = "Clique em Calcular para atualizar"
        }

        btnLimpar.setOnClickListener {
            limparCampos()
        }

        btnLimpar.setOnLongClickListener {
            chkDesconto.isChecked = false
            txtDesconto.text = "Desconto desativado"
            limparCampos()
            true
        }
    }

    private fun processarCompra() {
        val quantidade = edtQuantidade.text.toString().trim().toIntOrNull()

        if (quantidade == null || quantidade <= 0) {
            edtQuantidade.error = "Informe uma quantidade maior que zero"
            return
        }

        edtQuantidade.error = null
        val total = calcularTotal(quantidade, chkDesconto.isChecked)
        txtResultado.text = "Total: ${formatoMoeda.format(total)}"
    }

    private fun calcularTotal(quantidade: Int, aplicarDesconto: Boolean): Double {
        val subtotal = quantidade * precoLanche
        return if (aplicarDesconto) {
            // O desconto incide sobre o total da compra (10% de desconto)
            subtotal * 0.90
        } else {
            subtotal
        }
    }

    private fun limparCampos() {
        edtQuantidade.text.clear()
        edtQuantidade.error = null
        txtResultado.text = "Total: R$ 0,00"
        edtQuantidade.requestFocus()
    }
}