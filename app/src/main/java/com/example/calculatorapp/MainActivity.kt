package com.example.calculatorapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: EditText
    private val input = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.result_text)

        // 数字按钮点击事件
        setupNumberButton(R.id.button_0, "0")
        setupNumberButton(R.id.button_1, "1")
        setupNumberButton(R.id.button_2, "2")
        setupNumberButton(R.id.button_3, "3")
        setupNumberButton(R.id.button_4, "4")
        setupNumberButton(R.id.button_5, "5")
        setupNumberButton(R.id.button_6, "6")
        setupNumberButton(R.id.button_7, "7")
        setupNumberButton(R.id.button_8, "8")
        setupNumberButton(R.id.button_9, "9")
        setupNumberButton(R.id.button_dot, ".")

        // 操作符按钮点击事件
        setupOperatorButton(R.id.button_add, "+")
        setupOperatorButton(R.id.button_subtract, "-")
        setupOperatorButton(R.id.button_multiply, "*")
        setupOperatorButton(R.id.button_divide, "/")

        // 清除按钮点击事件
        val clearButton = findViewById<Button>(R.id.button_clear)
        clearButton.setOnClickListener {
            input.setLength(0)
            resultText.setText("")
        }

        // 等于按钮点击事件
        val equalButton = findViewById<Button>(R.id.button_equal)
        equalButton.setOnClickListener {
            try {
                val expression = Expression(input.toString())
                val result = expression.calculate()
                input.setLength(0)
                input.append(result)
                resultText.setText(result.toString())
            } catch (e: Exception) {
                Toast.makeText(this, "输入错误", Toast.LENGTH_SHORT).show()
                input.setLength(0)
                resultText.setText("")
            }
        }

        // 关于按钮点击事件
        val aboutButton = findViewById<Button>(R.id.button_about)
        aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupNumberButton(buttonId: Int, number: String) {
        val button = findViewById<Button>(buttonId)
        button.setOnClickListener {
            input.append(number)
            resultText.setText(input.toString())
        }
    }

    private fun setupOperatorButton(buttonId: Int, operator: String) {
        val button = findViewById<Button>(buttonId)
        button.setOnClickListener {
            input.append(operator)
            resultText.setText(input.toString())
        }
    }
}