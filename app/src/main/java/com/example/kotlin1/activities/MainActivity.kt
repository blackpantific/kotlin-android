package com.example.kotlin1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kotlin1.R
import com.example.kotlin1.activities.fragments.*
import com.example.kotlin1.models.Auto
import com.example.kotlin1.models.Task
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

enum class ColorOfToolBar {
    DEFAULT,
    CUSTOM
}

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener,
    FirstTaskFragment.Callbacks, ThirdTaskFragment.Callbacks {

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

        supportFragmentManager.addOnBackStackChangedListener() {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true) // show back button
                toolbar.setNavigationOnClickListener() {
                    onBackPressed();
                }
            } else {
                //show hamburger
                supportActionBar!!.setDisplayHomeAsUpEnabled(false);
                toggle.syncState();
                toolbar.setNavigationOnClickListener() {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        }

        navigationView.setNavigationItemSelectedListener(this)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            supportFragmentManager
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

        when (item.itemId) {
            R.id.save_number_task -> {
                setToolBarColor(ColorOfToolBar.DEFAULT)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, SaveNumberFragment())
                    .commit()
            }
            R.id.first_task -> {
                setToolBarColor(ColorOfToolBar.CUSTOM)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, FirstTaskFragment())
                    .commit()
            }
            R.id.second_task -> {
                setToolBarColor(ColorOfToolBar.DEFAULT)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, SecondTaskFragment())
                    .commit()
            }
            R.id.third_task -> {
                setToolBarColor(ColorOfToolBar.DEFAULT)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ThirdTaskFragment())
                    .commit()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setToolBarColor(type: ColorOfToolBar) {
        if (type == ColorOfToolBar.DEFAULT) {
            this.supportActionBar!!.setBackgroundDrawable(
                AppCompatResources.getDrawable(this, R.color.purple_500)
            )
        } else {
            this.supportActionBar!!.setBackgroundDrawable(
                AppCompatResources.getDrawable(this, R.color.teal_200)
            )
        }
    }

    override fun replaceFragment(selectedCar: Auto) {
        val fragment = FirstTaskDetailsFragment.newInstance(selectedCar)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onTaskSelected(task: Task?) {
        val fragment = TaskDetailsFragment.newInstance(task)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}