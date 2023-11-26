package com.nsadisha.android.ntask.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class OnboardingViewModel(application: Application) : AndroidViewModel(application) {
    private val _name = "name"
    private val defaultName = "unregistered_ntask_user"
    private val sharedPreferences = application.getSharedPreferences("ntask_prefs", Context.MODE_PRIVATE)

    fun readName(): String {
        return sharedPreferences.getString(_name, defaultName) ?: defaultName
    }

    fun writeName(value: String) {
        sharedPreferences.edit().putString(_name, value).apply()
    }

    fun isRegistered(): Boolean {
        return readName() != defaultName
    }
}