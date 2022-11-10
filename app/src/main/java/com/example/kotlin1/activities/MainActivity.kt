package com.example.kotlin1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kotlin1.R
import com.example.kotlin1.activities.fragments.FirstTaskFragment
import com.example.kotlin1.activities.fragments.SaveNumberFragment
import com.example.kotlin1.activities.fragments.SecondTaskFragment
import com.example.kotlin1.activities.fragments.ThirdTaskFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.opened, R.string.closed)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, SaveNumberFragment())
                .commit()
        }
    }

    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save_number_task -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, SaveNumberFragment())
                    .commit()
            }
            R.id.first_task -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, FirstTaskFragment())
                    .commit()
            }
            R.id.second_task -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, SecondTaskFragment())
                    .commit()
            }
            R.id.third_task -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ThirdTaskFragment())
                    .commit()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}