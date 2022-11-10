package com.example.kotlin1.activities.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.kotlin1.CarAdapter
import com.example.kotlin1.R
import com.example.kotlin1.activities.FirstTaskDetailsActivity
import com.example.kotlin1.helpers.DataSource
import com.example.kotlin1.models.Auto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial

private const val TAG = "FirstTaskFragment"

class FirstTaskFragment : Fragment() {

    private lateinit var fabButton: FloatingActionButton
    private lateinit var textOutput: TextView
    private lateinit var textInput: EditText
    private lateinit var listView: ListView
    private lateinit var hideListButton: Button
    private lateinit var showToastButton: Button
    private lateinit var switch: SwitchMaterial
    private lateinit var labelColorChange: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_task, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()
    }
}