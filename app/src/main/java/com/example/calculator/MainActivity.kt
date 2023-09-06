package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    private lateinit var txt_num1: TextView
    private lateinit var txt_num2: TextView
    private lateinit var txt_result: TextView
    private lateinit var txt_operator: TextView

    private var num1 = 0
    private var num2 = 0
    private var currentOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        txt_num1 = findViewById(R.id.txt_num1)
        txt_num2 = findViewById(R.id.txt_num2)
        txt_result = findViewById(R.id.txt_result)
        txt_operator = findViewById(R.id.txt_operator)
    }

    fun numberClick(view: View) {
        val btn = view as Button
        val num = btn.text.toString()
        if (currentOperator.isEmpty()) {
            num1 = num1 * 10 + num.toInt()
            txt_num1.text = num1.toString()
        } else {
            num2 = num2 *  10 + num.toInt()
            txt_num2.text = num2.toString()
        }
    }

    fun operatorClick(view: View) {
        // menyimpan operator ketika tombol operator (/, x, -, +) diklik
        val btn = view as Button
        currentOperator = btn.text.toString()
        txt_operator.text = currentOperator
    }

    fun resultClick(view: View) {
        // ketika menekan tombol = maka fungsi akan menghitung hasil dari bilangan yang diinputkan tadi
        var result = 0.0
        var num1 = num1.toDouble()
        var num2 = num2.toDouble()
        when (currentOperator) {
            "+" -> result = num1 + num2
            "-" -> result = num1 - num2
            "×" -> result = num1 * num2
            "÷" -> {
                if (num2 != 0.0) {
                    result = num1 / num2
                }
            }
        }
        if (result % 1 == 0.0) {
            // jika hasil bukan bukan desimal akan dikembalikan ke integer
            val intResult = result.toInt()
            txt_result.text = intResult.toString()
        } else {
            // jika hasil desimal akan dilakukan penyesuaian jumlah angka di belakang koma
            val decimalFormat = DecimalFormat("#." + "#".repeat(10))
            txt_result.text = decimalFormat.format(result)
        }
    }

    fun cClick(view: View) {
        // ketika menekan tombol C maka semua angka dan operator yang diinputkan tadi akan terhapus
        num1 = 0
        num2 = 0
        currentOperator = ""
        txt_operator.text = ""
        txt_num1.text = ""
        txt_num2.text = ""
        txt_result.text = ""
    }
}