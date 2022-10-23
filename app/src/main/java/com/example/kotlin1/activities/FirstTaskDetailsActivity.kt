package com.example.kotlin1.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin1.R
import com.example.kotlin1.models.Auto

private const val ARG_AUTO = "auto_key"

class FirstTaskDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_task_details)

        var res = intent.extras?.getSerializable(ARG_AUTO) as Auto
    }

    companion object {
        fun newIntent(packageContext: Context, auto: Auto): Intent {
            return Intent(packageContext, FirstTaskDetailsActivity::class.java).apply {
                putExtra(ARG_AUTO, auto)
            }
        }
    }
}