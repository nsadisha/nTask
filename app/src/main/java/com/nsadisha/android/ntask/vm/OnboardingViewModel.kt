package com.nsadisha.android.ntask.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class OnboardingViewModel(application: Application) : AndroidViewModel(application) {
    private val _name = "name"
    private val _default_name = "unregistered_ntask_user"
    private val sharedPreferences = application.getSharedPreferences("ntask_prefs", Context.MODE_PRIVATE)

    fun readName(): String {
        return sharedPreferences.getString(_name, _default_name) ?: _default_name
    }

    fun writeName(value: String) {
        sharedPreferences.edit().putString(_name, value).apply()
    }

    fun isRegistered(): Boolean {
        return readName() != _default_name
    }
}