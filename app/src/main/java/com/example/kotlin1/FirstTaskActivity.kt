package com.example.kotlin1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val TAG = "FirstTaskActivity"

class FirstTaskActivity : AppCompatActivity() {

    private lateinit var fabButton: FloatingActionButton
    private lateinit var textOutput: TextView
    private lateinit var textInput: EditText
    //поменять на листвью
    private lateinit var listView: Button
    private lateinit var hideListButton: Button
    private lateinit var showToastButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_task)

        supportActionBar!!.setBackgroundDrawable(
            AppCompatResources.getDrawable(this, R.color.teal_200))

        fabButton = findViewById(R.id.fab)
        textOutput = findViewById(R.id.text_output)
        textInput = findViewById(R.id.text_input)
        listView = findViewById(R.id.list_view)
        hideListButton = findViewById(R.id.hide_list_button)
        showToastButton = findViewById(R.id.invoke_toast)

        fabButton.setOnClickListener{
            textOutput.text = textInput.text
        }

        hideListButton.setOnClickListener {
            listView.isVisible = !listView.isVisible
            if(listView.isVisible){
                Log.d(TAG, "ListView is visible")
            }else{
                Log.d(TAG, "ListView is not visible")
            }
        }

        showToastButton.setOnClickListener {
            Toast.makeText(
                this,
                R.string.toast_text,
                Toast.LENGTH_SHORT)
                .show()
            Log.d(TAG, "Toast called")
        }
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, FirstTaskActivity::class.java)
        }
    }
}