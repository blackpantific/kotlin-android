package com.example.kotlin1

import android.content.Context
import android.preference.PreferenceManager

private const val PREF_NUMBER = "NumberToSave"

object Preferences {
    fun getStoredNumber(context: Context): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getInt(PREF_NUMBER, 0)!!
    }

    fun setStoredNumber(context: Context, number: Int) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putInt(PREF_NUMBER, number)
            .apply()
    }
}