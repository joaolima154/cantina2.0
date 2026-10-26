package com.example.cantinaeventos
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowCompat
import java.text.NumberFormat
import java.util.Locale
import android.widget.CheckBox
import kotlin.text.toInt

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
        ViewCompat.setOnApplyWindowInsetsListener(             findViewById(R.id.main)         ) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            val margin = (16 * resources.displayMetrics.density).toInt()             view.setPadding(
                    bars.left + margin, bars.top + margin, bars.right + margin, bars.bottom + margin
            )             insets


        }

}


        edtQuantidade = findViewById(R.id.edtQuantidade)
        txtResultado = findViewById(R.id.txtResultado)