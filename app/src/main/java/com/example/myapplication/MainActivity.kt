package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.mariuszgromada.math.mxparser.*


class MainActivity : AppCompatActivity() {

    private lateinit var previousCalculation: TextView
    private lateinit var display: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStandardCalculator = findViewById<Button>(R.id.button_standard)
        btnStandardCalculator.setOnClickListener {
            val intent = Intent(this, standardCalculator::class.java)
            startActivity(intent)
        }

        val btnAdvancedCalculator = findViewById<Button>(R.id.button_advanced)
        btnAdvancedCalculator.setOnClickListener {
            val intent = Intent(this, advancedCalculator::class.java)
            startActivity(intent)
        }

        val btnAbout = findViewById<Button>(R.id.button_about)
        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}