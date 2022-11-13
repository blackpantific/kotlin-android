package com.example.kotlin1.activities.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin1.R
import com.example.kotlin1.helpers.MathHelper
import com.example.kotlin1.models.Auto

private const val ARG_AUTO = "auto_key"
private const val ARG_NAT = "NAT"
private const val ARG_FIB = "FIB"
private const val ARG_COL = "COL"

class FirstTaskDetailsFragment  : Fragment() {

    private lateinit var naturalButton: Button
    private lateinit var fibonacciButton: Button
    private lateinit var collatzButton: Button
    private lateinit var natView: TextView
    private lateinit var fibView: TextView
    private lateinit var colView: TextView
    private lateinit var carDescription: TextView
    private lateinit var carTitle: TextView
    private lateinit var carIcon: ImageView

    private var naturalValue: Int = 0
    private var fibonacciValue: Int = 0
    private var collatzValue: Int = 12

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first_task_detail, container, false)

        val res = arguments?.getSerializable(ARG_AUTO) as Auto
        naturalValue = savedInstanceState?.getInt(ARG_NAT, 0) ?: 0
        fibonacciValue = savedInstanceState?.getInt(ARG_FIB, 0) ?: 0
        collatzValue = savedInstanceState?.getInt(ARG_COL, 0) ?: 12

        naturalButton = view.findViewById(R.id.nat_button)
        fibonacciButton = view.findViewById(R.id.fib_button)
        collatzButton = view.findViewById(R.id.col_button)
        natView = view.findViewById(R.id.nat_number)
        fibView = view.findViewById(R.id.fib_number)
        colView = view.findViewById(R.id.col_number)
        carDescription = view.findViewById(R.id.car_description)
        carTitle = view.findViewById(R.id.car_title)
        carIcon = view.findViewById(R.id.car_icon)

        natView.text = naturalValue.toString()
        fibView.text = fibonacciValue.toString()
        colView.text = collatzValue.toString()

        carDescription.text = res.description
        carTitle.text = res.title
        carIcon.setImageResource(res.icon.path)

        carDescription.movementMethod = ScrollingMovementMethod()

        naturalButton.setOnClickListener {
            naturalValue++
            natView.text = naturalValue.toString()
        }

        fibonacciButton.setOnClickListener {
            fibonacciValue++
            fibView.text = MathHelper.nthFibonacciTerm(fibonacciValue).toString()
        }

        collatzButton.setOnClickListener {
            collatzValue = MathHelper.getCollatzNumber(collatzValue)
            colView.text = collatzValue.toString()
        }

        return view
    }

    companion object {
        fun newInstance(selectedAuto: Auto): FirstTaskDetailsFragment {
            val args = Bundle().apply {
                putSerializable(ARG_AUTO, selectedAuto)
            }
            return FirstTaskDetailsFragment().apply {
                arguments = args
            }
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.apply {
            putInt(ARG_NAT, naturalValue)
            putInt(ARG_FIB, fibonacciValue)
            putInt(ARG_COL, collatzValue)
        }
    }
}