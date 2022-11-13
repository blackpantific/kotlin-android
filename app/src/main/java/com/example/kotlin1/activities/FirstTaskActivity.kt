package com.example.kotlin1.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.kotlin1.CarAdapter
import com.example.kotlin1.R
import com.example.kotlin1.helpers.DataSource
import com.example.kotlin1.models.Auto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial

private const val TAG = "FirstTaskActivity"

class FirstTaskActivity : AppCompatActivity() {

    private lateinit var fabButton: FloatingActionButton
    private lateinit var textOutput: TextView
    private lateinit var textInput: EditText
    private lateinit var listView: ListView
    private lateinit var hideListButton: Button
    private lateinit var showToastButton: Button
    private lateinit var switch: SwitchMaterial
    private lateinit var labelColorChange: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_task)

        supportActionBar!!.setBackgroundDrawable(
            AppCompatResources.getDrawable(this, R.color.teal_200)
        )

        fabButton = findViewById(R.id.fab)
        textOutput = findViewById(R.id.text_output)
        textInput = findViewById(R.id.text_input)
        listView = findViewById(R.id.list_view)
        switch = findViewById(R.id.switch_for_label)
        hideListButton = findViewById(R.id.hide_list_button)
        showToastButton = findViewById(R.id.invoke_toast)
        labelColorChange = findViewById(R.id.label_color_to_change)

        fabButton.setOnClickListener {
            textOutput.text = textInput.text
        }

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                labelColorChange.setTextColor(resources.getColor(R.color.purple_500, this.theme))
            } else {
                labelColorChange.setTextColor(resources.getColor(R.color.black, this.theme))
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
            Toast.makeText(
                this,
                R.string.toast_text,
                Toast.LENGTH_SHORT
            )
                .show()
            Log.d(TAG, "Toast called")
        }

        listView.adapter =
            CarAdapter(this, android.R.layout.simple_list_item_1, DataSource.getListOfCars())

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id -> // получаем выбранный пункт
                val selectedAuto = parent.getItemAtPosition(position) as Auto
                val intent = FirstTaskDetailsActivity.newIntent(this, selectedAuto)
                startActivity(intent)
            }
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, FirstTaskActivity::class.java)
        }
    }
}