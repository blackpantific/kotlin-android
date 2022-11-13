package com.example.kotlin1.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin1.R
import com.example.kotlin1.helpers.Preferences

class SaveNumberFragment : Fragment() {

    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var numberOutput: TextView
    private var currentNumber = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_save_number_task, container, false)

        currentNumber = Preferences.getStoredNumber(activity as AppCompatActivity)

        prevButton = view.findViewById(R.id.prev_button)
        nextButton = view.findViewById(R.id.next_button)
        numberOutput = view.findViewById(R.id.number_output)

        numberOutput.text = currentNumber.toString()

        prevButton.setOnClickListener {
            if (currentNumber != 0) {
                currentNumber--
                Preferences.setStoredNumber(activity as AppCompatActivity, currentNumber)
                numberOutput.text = currentNumber.toString()
            }
        }

        nextButton.setOnClickListener {
            currentNumber++
            Preferences.setStoredNumber(activity as AppCompatActivity, currentNumber)
            numberOutput.text = currentNumber.toString()
        }

        return view
    }
}