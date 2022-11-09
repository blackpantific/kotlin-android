package com.example.kotlin1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.kotlin1.R
import com.example.kotlin1.helpers.Preferences

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var numberOutput: TextView
    private lateinit var task1Button: Button
    private var currentNumber = 0
        //??
        set(value) {
        field = value
        Preferences.setStoredNumber(applicationContext, value)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentNumber = Preferences.getStoredNumber(applicationContext)

        prevButton = findViewById(R.id.prev_button)
        nextButton = findViewById(R.id.next_button)
        numberOutput = findViewById(R.id.number_output)
        task1Button = findViewById(R.id.task1_button)

        numberOutput.text = currentNumber.toString()

        prevButton.setOnClickListener {
            if (currentNumber != 0) {
                currentNumber--
                numberOutput.text = currentNumber.toString()
            }
        }

        nextButton.setOnClickListener {
            currentNumber++
            numberOutput.text = currentNumber.toString()
        }

        task1Button.setOnClickListener {
            val intent = FirstTaskActivity.newIntent(this@MainActivity)
            startActivity(intent)
        }
    }
}