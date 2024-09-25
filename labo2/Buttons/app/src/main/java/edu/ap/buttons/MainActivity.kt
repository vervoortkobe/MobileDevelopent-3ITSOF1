package edu.ap.buttons

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import edu.ap.buttons.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private var counter = 0
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        setContentView(R.layout.activity_main)

        val textViewCounter: TextView = findViewById(R.id.textViewCounter)
        val buttonIncrease: Button = findViewById(R.id.buttonIncrease)
        val buttonDecrease: Button = findViewById(R.id.buttonDecrease)

        buttonIncrease.setOnClickListener() {
            counter++
            textViewCounter.text = counter.toString()
            Log.d("MYTAG", counter.toString())
        }

        buttonDecrease.setOnClickListener() {
            counter--
            textViewCounter.text = counter.toString()
            Log.d("MYTAG", counter.toString())
        }

        setContentView(R.layout.activity_login)
    }
}