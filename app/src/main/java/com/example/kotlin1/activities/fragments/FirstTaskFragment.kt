package com.example.kotlin1.activities.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin1.CarAdapter
import com.example.kotlin1.R
import com.example.kotlin1.helpers.DataSource
import com.example.kotlin1.models.Auto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

private const val TAG = "FirstTaskFragment"
private const val ARG_SWITCH = "SWITCH_DATA"
private const val ARG_EXP_LT = "EXPAND_LIST"
private const val ARG_OUT_TXT = "OUT_TXT"
private const val ARG_IN_TXT = "IN_TXT"

class FirstTaskFragment : Fragment() {

    interface Callbacks {
        fun replaceFragment(selectedCar: Auto)
    }

    private var callbacks: Callbacks? = null
    private lateinit var fabButton: FloatingActionButton
    private lateinit var textOutput: TextView
    private lateinit var textInput: EditText
    private lateinit var listView: ListView
    private lateinit var hideListButton: Button
    private lateinit var showToastButton: Button
    private lateinit var switch: SwitchMaterial
    private lateinit var labelColorChange: TextView
    private lateinit var thisFragmentActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_task, container, false)

        thisFragmentActivity = activity as AppCompatActivity

        fabButton = view.findViewById(R.id.fab)
        textOutput = view.findViewById(R.id.text_output)
        textInput = view.findViewById(R.id.text_input)
        listView = view.findViewById(R.id.list_view)
        switch = view.findViewById(R.id.switch_for_label)
        hideListButton = view.findViewById(R.id.hide_list_button)
        showToastButton = view.findViewById(R.id.invoke_toast)
        labelColorChange = view.findViewById(R.id.label_color_to_change)

        switch.isChecked = savedInstanceState?.getBoolean(ARG_SWITCH) ?: false
        listView.visibility = savedInstanceState?.getInt(ARG_EXP_LT) ?: View.VISIBLE
        textOutput.text = savedInstanceState?.getString(ARG_OUT_TXT)


        fabButton.setOnClickListener {
            textOutput.text = textInput.text
        }

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                labelColorChange.setTextColor(resources.getColor(R.color.purple_500, thisFragmentActivity.theme))
            } else {
                labelColorChange.setTextColor(resources.getColor(R.color.black, thisFragmentActivity.theme))
            }
        }

        hideListButton.setOnClickListener {
            if (listView.visibility == View.INVISIBLE) {
                listView.visibility = View.VISIBLE
                Log.d(TAG, "ListView is visible")
            } else {
                listView.visibility = View.INVISIBLE
                Log.d(TAG, "ListView is not visible")
            }
        }

        showToastButton.setOnClickListener {
            Snackbar.make(view, R.string.snackbar_text, Snackbar.LENGTH_LONG).show()
            Log.d(TAG, "Toast called")
        }

        listView.adapter =
            CarAdapter(thisFragmentActivity, android.R.layout.simple_list_item_1, DataSource.getListOfCars())

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id -> // получаем выбранный пункт
                val selectedAuto = parent.getItemAtPosition(position) as Auto
                callbacks?.replaceFragment(selectedAuto)
            }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putBoolean(ARG_SWITCH, switch.isChecked)
            putInt(ARG_EXP_LT, listView.visibility)
            putString(ARG_OUT_TXT, textOutput.text.toString())
            putString(ARG_IN_TXT, textInput.text.toString())
        }
    }
}