package com.example.kotlin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var numberOutput: TextView
    private var currentNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentNumber = Preferences.getStoredNumber(applicationContext)

        prevButton = findViewById(R.id.prev_button)
        nextButton = findViewById(R.id.next_button)
        numberOutput = findViewById(R.id.number_output)

        numberOutput.text = currentNumber.toString()

        prevButton.setOnClickListener{
            if(currentNumber != 0){
                currentNumber--
                numberOutput.text = currentNumber.toString()
            }
        }

        nextButton.setOnClickListener{
            currentNumber++
            numberOutput.text = currentNumber.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        Preferences.setStoredNumber(applicationContext, currentNumber)
    }
}